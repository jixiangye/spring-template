/**
 * Created by yejx on 2017/3/23.
 */
function getInputValues(node){
    var params = {};
    var inputs = node.find("input");
    for(var i=0;i<inputs.length;i++){
        params[inputs[i].name] = inputs[i].value;
    }
    return params;
}