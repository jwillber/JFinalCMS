/*
 * 
 * JavaScript - List
 */

$().ready( function() {

	var $listForm = $("#listForm");
	var $deleteButton = $("#deleteButton");
	var $refreshButton = $("#refreshButton");
	var $listTable = $("#listTable");
	var $selectAll = $("#selectAll");
	var $ids = $("#listTable input[name='ids']");
	var $contentRow = $("#listTable tr:gt(0)");
	var $pageSize = $("#pageSize");
	var $pageNumber = $("#pageNumber");
	var $totalPage = $("#totalPage");
	
	// 删除
	$deleteButton.click( function() {
		var $this = $(this);
		if ($this.hasClass("disabled")) {
			return false;
		}
		var $checkedIds = $("#listTable input[name='ids']:enabled:checked");
		if(confirm("您确定要删除吗？")){
			$.ajax({
				url: "delete",
				type: "POST",
				data: $checkedIds.serialize(),
				dataType: "json",
				cache: false,
				success: function(message) {
					if (message.type == "success") {
						$checkedIds.closest("tr").remove();
						if ($listTable.find("tr").size() <= 1) {
							setTimeout(function() {
								location.reload(true);
							}, 3000);
						}
					}
					$deleteButton.addClass("disabled");
					$selectAll.prop("checked", false);
					$checkedIds.prop("checked", false);
				}
			});
		}
		return false;
	});
	
	// 刷新
	$refreshButton.click( function() {
		location.reload(true);
		return false;
	});
	
	// 全选
	$selectAll.click( function() {
		var $this = $(this);
		var $enabledIds = $("#listTable input[name='ids']:enabled");
		if ($this.prop("checked")) {
			$enabledIds.prop("checked", true);
			if ($enabledIds.filter(":checked").size() > 0) {
				$deleteButton.removeClass("disabled");
				$contentRow.addClass("selected");
			} else {
				$deleteButton.addClass("disabled");
			}
		} else {
			$enabledIds.prop("checked", false);
			$deleteButton.addClass("disabled");
			$contentRow.removeClass("selected");
		}
	});
	
	// 选择
	$ids.click( function() {
		var $this = $(this);
		if ($this.prop("checked")) {
			$this.closest("tr").addClass("selected");
			$deleteButton.removeClass("disabled");
		} else {
			$this.closest("tr").removeClass("selected");
			if ($("#listTable input[name='ids']:enabled:checked").size() > 0) {
				$deleteButton.removeClass("disabled");
			} else {
				$deleteButton.addClass("disabled");
			}
		}
	});
	
	// 页码
	$pageNumber.keypress(function(event) {
		return (event.which >= 48 && event.which <= 57) || event.which == 8 || (event.which == 13 && $(this).val().length > 0);
	});
	
	// 表单提交
	$listForm.submit(function() {
		if (!/^\d*[1-9]\d*$/.test($pageNumber.val())) {
			$pageNumber.val("1");
		}
		if(parseInt($pageNumber.val())>parseInt($totalPage.val())){
			$pageNumber.val($totalPage.val());
		}
	});
	
	// 页码跳转
	$.pageSkip = function(pageNumber) {
		$pageNumber.val(pageNumber);
		$listForm.submit();
		return false;
	};
	
	// 列表查询
	if (location.search != "") {
		addCookie("listQuery", location.search, {expires: 10 * 60, path: ""});
	} else {
		removeCookie("listQuery", {path: ""});
	}

});