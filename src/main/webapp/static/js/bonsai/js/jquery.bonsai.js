(function($){
  $.fn.bonsai = function(options) {
    var args = arguments;
    return this.each(function() {
      var bonsai = $(this).data('bonsai');
      if (!bonsai) {
        bonsai = new Bonsai(this, options);
        $(this).data('bonsai', bonsai);
      }
      if (typeof options == 'string') {
        var method = options;
        bonsai[method].apply(bonsai, [].slice.call(args, 1));
      }
    });
  };
  $.bonsai = {};
  $.bonsai.defaults = {
    expandAll: false, // boolean expands all items
    expand: null, // function to expand an item
    collapse: null, // function to collapse an item
    checkboxes: false, // requires jquery.qubit
    // createCheckboxes: creates checkboxes for each list item.
    //
    // The name and value for the checkboxes can be declared in the
    // markup using `data-name` and `data-value`.
    //
    // The name is inherited from parent items if not specified.
    //
    // Checked state can be indicated using `data-checked`.
    createCheckboxes: false,
    // handleDuplicateCheckboxes: adds onChange bindings to update
    // any other checkboxes that have the same value.
    handleDuplicateCheckboxes: false,
    selectAllExclude: null
  };
  var Bonsai = function(el, options) {
    options = options || {};
    this.options = $.extend({}, $.bonsai.defaults, options);
    this.el = $(el).addClass('bonsai').data('bonsai', this);
    this.update();
    if (this.options.expandAll) this.expandAll();
  };
  Bonsai.prototype = {
    toggle: function(listItem) {
      if (!$(listItem).hasClass('expanded')) {
        this.expand(listItem);
      }
      else {
        this.collapse(listItem);
      }
    },
    expand: function(listItem) {
      this.setExpanded(listItem, true);
    },
    collapse: function(listItem) {
      this.setExpanded(listItem, false);
    },
    setExpanded: function(listItem, expanded) {
      listItem = $(listItem);
      if (listItem.length > 1) {
        var self = this;
        listItem.each(function() {
          self.setExpanded(this, expanded);
        });
        return;
      }
      if (expanded) {
        if (!listItem.data('subList')) return;
        listItem = $(listItem).addClass('expanded')
          .removeClass('collapsed');
        $(listItem.data('subList')).css('height', 'auto');
      }
      else {
        listItem = $(listItem).addClass('collapsed')
          .removeClass('expanded');
        $(listItem.data('subList')).height(0);
      }
    },
    expandAll: function() {
      this.expand(this.el.find('li'));
    },
    collapseAll: function() {
      this.collapse(this.el.find('li'));
    },
    update: function() {
      var self = this;
      var isRootNode = false;
      // store the scope in the options for child nodes
      if (!this.options.scope || this.options.scope === this.el) {
        this.options.scope = this.el;
        isRootNode = true;
      }

      // look for a nested list (if any)
      this.el.children().each(function() {
        var item = $(this);
        if (self.options.createCheckboxes) self.insertCheckbox(item);
        // insert a thumb if it doesn't already exist
        if (item.children().filter('.thumb').length == 0) {
          var thumb = $('<div class="thumb"></div>');
          item.prepend(thumb);
          thumb.on('click', function() {
            self.toggle(item);
          });
        }
        var subLists = item.children().filter('ol, ul');
        // if there are no child lists
        if (subLists.length == 0) {
          item.removeClass('has-children');
        }
        // if there is a child list
        subLists.each(function() {
          // that is not empty
          if ($('li', this).length == 0) {
            return;
          }
          // then this el has children
          item.addClass('has-children')
            // attach the sub-list to the item
            .data('subList', this);
          // collapse the nested list
          if (item.hasClass('expanded')) {
            self.expand(item);
          }
          else {
            self.collapse(item);
          }
          // handle any deeper nested lists
          $(this).bonsai(self.options);
        });
      });
      // if this is root node of the tree
      if (isRootNode) {
        if (this.options.checkboxes) this.el.qubit(this.options);
        if (this.options.addExpandAll) this.addExpandAllLink();
        if (this.options.addSelectAll) this.addSelectAllLink();
      }
      this.expand = this.options.expand || this.expand;
      this.collapse = this.options.collapse || this.collapse;
    },
    insertCheckbox: function(listItem) {
      if (listItem.find('> input[type=checkbox]').length) return;
      var id = this.generateId(listItem),
          checkbox = $('<input type="checkbox" name="'
            + this.getCheckboxName(listItem) + '" id="' + id + '" /> '
          ),
          children = listItem.children(),
          // get the first text node for the label
          text = listItem.contents().filter(function() {
            return this.nodeType == 3;
          }).first();
      checkbox.val(listItem.data('value'));
      checkbox.prop('checked', listItem.data('checked'))
      children.remove();
      listItem.append(checkbox)
        .append(
          $('<label for="' + id + '">').append(text ? text : children.first())
        )
        .append(text ? children : children.slice(1));
      if (this.options.handleDuplicateCheckboxes) {
        this.handleDuplicates(checkbox);
      }
    },
    handleDuplicates: function(checkbox) {
      var self = this,
          checkbox = $(checkbox);
      checkbox.bind('change', function(e) {
        var isChecked = checkbox.prop('checked');
        if (this.value) {
          e.doneIds = e.doneIds || [];
          e.doneIds.push(this.id);
          // select all duplicate checkboxes within the same scope
          var selector = 'input[type=checkbox]'
              + '[value="' + checkbox.val() + '"]'
              + '[name="' + checkbox.attr('name') + '"]'
              + (isChecked ? ':not(:checked)' : ':checked');
          self.options.scope.find(selector)
            .filter(function() {
              return e.doneIds.indexOf(this.id) === -1;
            })
            .each(function() {
              // copy checked and indeterminate to the duplicate
              $(this).prop({
                  checked: isChecked,
                  indeterminate: $(this).prop('indeterminate')
                })
                .trigger({
                  type: 'change',
                  doneIds: e.doneIds
                });
            });
        }
        return true;
      });
    },
    idPrefix: 'checkbox-',
    generateId: function(listItem) {
      do {
        var id = this.idPrefix + Bonsai.uniqueId++;
      }
      while($('#' + id).length > 0);
      return id;
    },
    getCheckboxName: function(listItem) {
      return listItem.data('name')
        || listItem.parents().filter('[data-name]').data('name');
    },
    addExpandAllLink: function() {
      var self = this,
          scope = this.options.scope;
      $('<div class="expand-all">')
        .append($('<a class="all">Expand all</a>')
          .css('cursor', 'pointer')
          .bind('click', function() {
            self.expandAll();
          })
      )
        .append('<i class="separator"></i>')
        .append($('<a class="none">Collapse all</a>')
          .css('cursor', 'pointer')
          .bind('click', function() {
            self.collapseAll();
          })
      )
        .insertBefore(this.el);
    },
    addSelectAllLink: function() {
      var scope = this.options.scope,
          self = this;
      function getCheckboxes() {
        // return all checkboxes that are not in hidden list items
        return scope.find('li')
          .filter(self.options.selectAllExclude || function() {
            return $(this).css('display') != 'none';
          })
          .find('> input[type=checkbox]');
      }
      $('<div class="check-all">')
        .append($('<a class="all">Select all</a>')
          .css('cursor', 'pointer')
          .bind('click', function() {
            getCheckboxes().prop({
              checked: true,
              indeterminate: false
            });
          })
      )
        .append('<i class="separator"></i>')
        .append($('<a class="none">Select none</a>')
          .css('cursor', 'pointer')
          .bind('click', function() {
            getCheckboxes().prop({
              checked: false,
              indeterminate: false
            });
          })
      )
        .insertAfter(this.el);
    },
    setCheckedValues: function(values) {
      var all = this.options.scope.find('input[type=checkbox]');
      $.each(values, function(key, value) {
        all.filter('[value="' + value + '"]')
          .prop('checked', true)
          .trigger('change');
      });
    }
  };
  $.extend(Bonsai, {
    uniqueId: 0
  });
}(jQuery));
