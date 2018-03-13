/*
 * 
 * JavaScript - lSelect
 */

(function($) {
	$.fn.extend({
		lSelect: function(options) {
			var settings = {
				choose: "请选择...",
				emptyValue: "",
				url: null,
				type: "GET"
			};
			$.extend(settings, options);
			
			var cache = {};
			return this.each(function() {
				var lSelectId = new Date().getTime();
				var $input = $(this);
				var treePath = $input.attr("treePath");
				
				if (treePath != null && treePath != "") {
					var ids = (treePath + $input.val() + ",").split(",");
					var $position = $input;
					for (var i = 1; i < ids.length; i ++) {
						$position = addSelect($position, ids[i - 1], ids[i]);
					}
				} else {
					addSelect($input, null, null);
				}
				
				function addSelect($position, parentId, currentId) {
					$position.nextAll("." + lSelectId).remove();
					if ($position.is("." + lSelectId) && (parentId == null || parentId == "")) {
						return false;
					}
					if (cache[parentId] == null) {
						$.ajax({
							url: settings.url,
							type: settings.type,
							data: parentId != null ? {parentId: parentId} : null,
							dataType: "json",
							cache: false,
							async: false,
							success: function(data) {
								cache[parentId] = data;
							}
						});
					}
					var data = cache[parentId];
					if ($.isEmptyObject(data)) {
						return false;
					}
					var select = '<div class="col-sm-3 '+lSelectId+'"><select class="form-control">';
					if (settings.emptyValue != null && settings.choose != null) {
						select += '<option value="' + settings.emptyValue + '">' + settings.choose + '</option>';
					}
					$.each(data, function(i, option) {
						if(option.value == currentId) {
							select += '<option value="' + option.value + '" selected="selected">' + option.name + '</option>';
						} else {
							select += '<option value="' + option.value + '">' + option.name + '</option>';
						}
					});
					select += '</select></div>';
					$(select).insertAfter($position).find("select").on("change", function() {
						var $this = $(this);
						if ($this.val() == "") {
							var $prev = $this.prev("select[name=" + lSelectId + "]");
							if ($prev.size() > 0) {
								$input.val($prev.val());
							} else {
								$input.val(settings.emptyValue);
							}
						} else {
							$input.val($this.val());
						}
						addSelect($this.parent(), $this.val(), null);
					});
					return $position.parent().find("."+lSelectId+":last");
				}
			});
			
		}
	});
})(jQuery);