jQuery.fn.extend({
    values: function () {
        var params = {};
        var inputs = $(this).find("input");
        for (var i = 0; i < inputs.length; i++) {
            params[inputs[i].name] = inputs[i].value;
        }
        return params;

    },

    table: function (params, datas) {
        $(this).empty();

        var head = "<tr>";
        for (var i = 0; i < params.length; i++) {
            head += "<th>" + params[i].title + "</th>";
        }
        head += "</tr>";
        $(this).append(head);

        for (var i = 0; i < datas.length; i++) {
            var item = "<tr>";
            for (var j = 0; j < params.length; j++) {
                var formatter = params[j].formatter || function (d) {
                        return d
                    };
                var data = datas[i][params[j].field];
                item += "<td>" + (formatter(data, datas[i]) || "") + "</td>";
            }
            item += "</tr>";
            $(this).append(item);
        }

    }
});

jQuery.extend({
    alert: function (options) {
        options = options || {};
        var _body = $(document.body);
        var _confirmModal = $(getAlertModal()).appendTo(_body);
        var modalTitle = _confirmModal.find('.modal-title');
        var modalBody = _confirmModal.find('.modal-body');
        var modalFooter = _confirmModal.find('.modal-footer');
        modalTitle.html(options.title);
        modalBody.html(options.msg).css("text-align", "center");
        ;
        _confirmModal.find('.modal-dialog').css('width', options.width);
        _confirmModal.find(".close").click(function () {
            _confirmModal.remove();
        });
        _confirmModal.find("button[data-dismiss='modal']").click(function () {
            _confirmModal.remove();
            typeof options.ok == 'function' && options.ok();
        });

        _confirmModal.modal('show');
    },
    confirm: function (options) {
        options = options || {};
        var _body = $(document.body);
        var _confirmModal = $(getConfirmModal()).appendTo(_body);
        var modalTitle = _confirmModal.find('.modal-title');
        var modalBody = _confirmModal.find('.modal-body');
        var modalFooter = _confirmModal.find('.modal-footer');
        modalTitle.html(options.title);
        modalBody.html(options.msg).css("text-align", "center");
        _confirmModal.find('.modal-dialog').css('width', options.width);
        _confirmModal.find(".close").click(function () {
            _confirmModal.remove();
        });
        _confirmModal.find("button[data-dismiss='modal']").click(function () {
            _confirmModal.remove();
            typeof options.cancel == 'function' && options.cancel();
        });
        _confirmModal.find(".ok").click(function () {
            _confirmModal.remove();
            typeof options.ok == 'function' && options.ok();
        });
        _confirmModal.modal('show');
    }
});

function getConfirmModal() {
    return ['<div class="modal fade" data-backdrop="false" data-show="false" data-keyboard="false" role="dialog" >',
        '    <div class="modal-dialog modal-sm">',
        '        <div class="modal-content">',
        '            <div class="modal-header">',
        '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>',
        '                <h4 class="modal-title"></h4>',
        '            </div>',
        '            <div class="modal-body"></div>',
        '<div class="modal-footer">',
        '<button type="button" class="btn btn-primary ok">确定</button>',
        '<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>',
        '</div>',
        '        </div>',
        '    </div>',
        '</div>'].join("");
}

function getAlertModal() {
    return ['<div class="modal fade" data-backdrop="false" data-show="false" data-keyboard="false" role="dialog" >',
        '    <div class="modal-dialog modal-sm">',
        '        <div class="modal-content">',
        '            <div class="modal-header">',
        '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>',
        '                <h4 class="modal-title"></h4>',
        '            </div>',
        '            <div class="modal-body"></div>',
        '<div class="modal-footer">',
        '<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>',
        '</div>',
        '        </div>',
        '    </div>',
        '</div>'].join("");
}