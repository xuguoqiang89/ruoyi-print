$(function(){
    var selObj = document.getElementById("print_type");
    var type = selObj.value;
    if(selObj!=null && type!=null){
        initTempData();
    }
});

/*var LODOP;
function CreatePrintPageKBZ() {
    LODOP = getLodop();//1. 获取 LODOP
    var td_content = _editor_.getContent();
    var strFormHtml="<body>"+td_content+"</body>";
    LODOP.ADD_PRINT_HTML(0,0,"210mm","297mm",strFormHtml);//宽122mm 高62mm
};

function previewKBZ() {
    CreatePrintPageKBZ();
    LODOP.PREVIEW();
}*/

function initTempData(){
    //获取下拉选项
    var selObj = document.getElementById("print_type");
    var type = selObj.value;
    var dataObj = document.getElementById("print_data");
    var html = '';
    if("01"==type){
        html = initBookData();
    }else if("02"==type){
        html = initBuyData();
    }else if("03"==type){
        html = initContractData();
    }else if("04"==type){
        html = initPayData();
    }else if("05"==type){
        html = initRoomChangeData();
    }else if("06"==type){
        html = initPayModeChangeData();
    }else if("07"==type){
        html = initRefundData();
    }else if("08"==type){
        html = initClientChangeData();
    }else if("09"==type){
        html = initDiscountData();
    }else if("10"==type){
        html = initVipData();
    }else if('11'==type){
        html = initShouJuData();
    }
    dataObj.innerHTML = html;
}

function initBookData(){
    var codes = ["${djh}","${ydrq}","${khxm}","${lxfs}","${zjh}","${lxdz}","${fymc}","${xmmc}","${zqymc}","${loudong}","${danyuan}","${fanghao}",
        "${fyxsmj}","${fytnmj}","${fyjzmj}","${fyhx}","${zygw}","${dj}","${dj_rmb}","${ysdj}","${ysdj_rmb}","${yddj}","${ydzj}","${ydzj_rmb}","${ndrgrq}","${beizhu}","${zdr}","${zygw}","${ydze}","${ydze_rmb}",
        "${bdj}","${bzj}","${bzj_rmb}","${cw_mc}","${cw_mj}","${cw_dj}","${cw_zj}","${cw_ts}","${cw_zmj}","${cw_hj}","${cw_hj_rmb}",
        "${ccs_mc}","${ccs_mj}","${ccs_dj}","${ccs_zj}","${ccs_ts}","${ccs_zmj}","${ccs_hj}","${ccs_hj_rmb}",
        "${fsf_mc}","${fsf_mj}","${fsf_dj}","${fsf_zj}","${fsf_ts}","${fsf_zmj}","${fsfze}","${fsfze_rmb}","${fsf}"];
    var names = ["单据号","预订日期","客户姓名","联系方式","证件号","联系地址","房源名称","项目名称","子区域名称","楼栋","单元","房号",
        "销售面积","套内面积","建筑面积","户型","置业顾问","订金","订金(大写)","已交订金","已交订金(大写)","预订单价","预订总价","预订总价(大写)","拟认购日期","备注","制单人","置业顾问","预订单总额","预订单总额(大写)",
        "表单价","表总价","表总价(大写)","车位名称","车位面积","车位单价","车位总价","车位套数","车位总面积","车位合计总额","车位合计总额(大写)",
        "储藏室名称","储藏室面积","储藏室单价","储藏室总价","储藏室套数","储藏室总面积","储藏室合计总额","储藏室合计总额(大写)",
        "附属房名称","附属房面积","附属房单价","附属房总价","附属房套数","附属房总面积","附属房总额","附属房总额(大写)","附属房列表"];
    var html = "<div style='overflow-y: auto;height: 500px;' class='list_table'><table style='width: 90%;'>";
    for(var i=0;i<codes.length;i++){
        html += '<tr><td style="width:35%;text-align: center;vertical-align: middle;">'+names[i]+'</td><td style="padding:1px;"><input class="form-control" style="width: 100%;cursor: pointer;font-size: 14px;" value="'+codes[i]+'" onclick="copyContent(this);" readonly/></td></tr>';
    }
    html+= '</table></div>';
    return html;
}

function initBuyData(){
    var codes = ["${djh}","${rgrq}","${khxm}","${lxfs}","${zjh}","${lxdz}","${zygw}","${gtqyr}",
                "${fymc}","${xmmc}","${zqymc}","${loudong}","${danyuan}","${fanghao}","${fyhx}","${fyjzmj}","${fytnmj}","${fyxsmj}",
                "${dj}","${dj_rmb}","${bdj}","${bzj}","${bzj_rmb}","${rgdj}","${rgzj}","${rgzj_rmb}","${zk}","${ndqyrq}","${zdr}",
                "${yhfa}","${yhze}","${yhze_rmb}","${fjtk}","${beizhu}","${fkfs}","${dkyh}",
                "${sydkje}","${sydkje_rmb}","${gjjdkje}","${gjjdkje_rmb}","${dkze}","${dkze_rmb}",
                "${sfk}","${sfk_rmb}","${sfk_rq}","${ycx}","${ycx_rmb}","${ycx_rq}",
                "${fqk}","${fqk_rmb}","${fqk_rq}","${fq_qs}","${jhdj}","${jhdj_rmb}","${jhdj_rq}",
                "${rgze}","${rgze_rmb}",
                "${cw_mc}","${cw_mj}","${cw_dj}","${cw_zj}","${cw_ts}","${cw_zmj}","${cw_hj}","${cw_hj_rmb}",
                "${ccs_mc}","${ccs_mj}","${ccs_dj}","${ccs_zj}","${ccs_ts}","${ccs_zmj}","${ccs_hj}","${ccs_hj_rmb}",
                "${fsf_mc}","${fsf_mj}","${fsf_dj}","${fsf_zj}","${fsf_ts}","${fsf_zmj}","${fsfze}","${fsfze_rmb}","${fsf}"];

    var names = ["单据号","认购日期","客户姓名","联系方式","证件号","联系地址","置业顾问","共同权益人",
                "房源名称","项目名称","子区域名称","楼栋","单元","房号","户型","建筑面积","套内面积","销售面积",
                "定金","定金(大写)","表单价","表总价","表总价(大写)","认购单价","认购总价","认购总价(大写)","折扣","拟签约日期","制单人",
                "优惠方案","优惠总额","优惠总额(大写)","附件条款","备注","付款方式","贷款银行",
                "商业贷款金额","商业贷款金额(大写)","公积金贷款金额","公积金贷款金额(大写)","贷款总额","贷款总额(大写)",
                "首付款金额","首付款金额(大写)","首付款日期","一次性款金额","一次性款金额(大写)","一次性款日期",
                "分期款金额","分期款金额(大写)","分期款日期","分期期数","计划定金","计划定金(大写)","计划定金日期",
                "认购单总额","认购单总额(大写)",
                "车位名称","车位面积","车位单价","车位总价","车位套数","车位总面积","车位合计总额","车位合计总额(大写)",
                "储藏室名称","储藏室面积","储藏室单价","储藏室总价","储藏室套数","储藏室总面积","储藏室合计总额","储藏室合计总额(大写)",
                "附属房名称","附属房面积","附属房单价","附属房总价","附属房套数","附属房总面积","附属房总额","附属房总额(大写)","附属房列表"];
    var html = "<div style='overflow-y: auto;height: 500px;' class='list_table'><table style='width: 90%;'>";
    for(var i=0;i<codes.length;i++){
        html += '<tr><td style="width:35%;text-align: center;vertical-align: middle;">'+names[i]+'</td><td style="padding:1px;"><input class="form-control" style="width: 100%;cursor: pointer;font-size: 14px;" value="'+codes[i]+'" onclick="copyContent(this);" readonly/></td></tr>';
    }
    html+= '</table></div>';
    return html;
}

function initContractData(){
    var codes = ["${djh}","${qyrq}","${khxm}","${lxfs}","${zjh}","${lxdz}","${zygw}","${gtqyr}",
        "${fymc}","${xmmc}","${zqymc}","${loudong}","${danyuan}","${fanghao}","${fyhx}","${zshth}","${fyjzmj}","${fytnmj}","${fyxsmj}",
        "${bdj}","${bzj}","${bzj_rmb}","${qydj}","${qyzj}","${qyzj_rmb}","${zk}","${jhjfrq}","${zdr}",
        "${yhfa}","${yhze}","${yhze_rmb}","${fjtk}","${beizhu}","${fkfs}","${dkyh}",
        "${sydkje}","${sydkje_rmb}","${gjjdkje}","${gjjdkje_rmb}","${dkze}","${dkze_rmb}",
        "${sfk}","${sfk_rmb}","${sfk_rq}","${ycx}","${ycx_rmb}","${ycx_rq}",
        "${fqk}","${fqk_rmb}","${fqk_rq}","${fq_qs}",
        "${qyze}","${qyze_rmb}",
        "${cw_mc}","${cw_mj}","${cw_dj}","${cw_zj}","${cw_ts}","${cw_zmj}","${cw_hj}","${cw_hj_rmb}",
        "${ccs_mc}","${ccs_mj}","${ccs_dj}","${ccs_zj}","${ccs_ts}","${ccs_zmj}","${ccs_hj}","${ccs_hj_rmb}",
        "${fsf_mc}","${fsf_mj}","${fsf_dj}","${fsf_zj}","${fsf_ts}","${fsf_zmj}","${fsfze}","${fsfze_rmb}","${fsf}"];

    var names = ["单据号","签约日期","客户姓名","联系方式","证件号","联系地址","置业顾问","共同权益人",
        "房源名称","项目名称","子区域名称","楼栋","单元","房号","户型","正式合同号","建筑面积","套内面积","销售面积",
        "表单价","表总价","表总价(大写)","签约单价","签约总价","签约总价(大写)","折扣","计划交房日期","制单人",
        "优惠方案","优惠总额","优惠总额(大写)","附件条款","备注","付款方式","贷款银行",
        "商业贷款金额","商业贷款金额(大写)","公积金贷款金额","公积金贷款金额(大写)","贷款总额","贷款总额(大写)",
        "首付款金额","首付款金额(大写)","首付款日期","一次性款金额","一次性款金额(大写)","一次性款日期",
        "分期款金额","分期款金额(大写)","分期款日期","分期期数",
        "签约单总额","签约单总额(大写)",
        "车位名称","车位面积","车位单价","车位总价","车位套数","车位总面积","车位合计总额","车位合计总额(大写)",
        "储藏室名称","储藏室面积","储藏室单价","储藏室总价","储藏室套数","储藏室总面积","储藏室合计总额","储藏室合计总额(大写)",
        "附属房名称","附属房面积","附属房单价","附属房总价","附属房套数","附属房总面积","附属房总额","附属房总额(大写)","附属房列表"];
    var html = "<div style='overflow-y: auto;height: 500px;' class='list_table'><table style='width: 90%;'>";
    for(var i=0;i<codes.length;i++){
        html += '<tr><td style="width:35%;text-align: center;vertical-align: middle;">'+names[i]+'</td><td style="padding:1px;"><input class="form-control" style="width: 100%;cursor: pointer;font-size: 14px;" value="'+codes[i]+'" onclick="copyContent(this);" readonly/></td></tr>';
    }
    html+= '</table></div>';
    return html;
}

function initPayData(){
    var codes = ["${fymc}","${xmmc}","${zqymc}","${loudong}","${danyuan}","${fanghao}","${khxm}","${lxfs}","${zjh}",
        "${lxdz}","${skrq}","${skr}","${fkxs}","${skje}","${skje_rmb}","${bdwyhzh}","${dfyh}","${sxf}","${fwlx}","${bczrje}","${bczrje}",
        "${djhjje}","${djhjje_rmb}","${kxmc}","${kxlb}","${sjh}","${fph}","${kpr}","${kprq}","${beizhu}"];

    var names = ["房源名称","项目名称","子区域名称","楼栋","单元","房号","客户姓名","联系方式","证件号",
        "联系地址","收款日期","收款人","付款形式","收款金额","收款金额(大写)","本单位银行及账号","对方银行","手续费","房屋类型","本次转入金额","本次转入金额(大写)",
        "单据合计金额","单据合计金额(大写)","款项名称","款项类型","收据号","发票号","开票人","开票日期","备注"];
    var html = "<div style='overflow-y: auto;height: 500px;' class='list_table'><table style='width: 90%;'>";
    for(var i=0;i<codes.length;i++){
        html += '<tr><td style="width:35%;text-align: center;vertical-align: middle;">'+names[i]+'</td><td style="padding:1px;"><input class="form-control" style="width: 100%;cursor: pointer;font-size: 14px;" value="'+codes[i]+'" onclick="copyContent(this);" readonly/></td></tr>';
    }
    html+= '</table></div>';
    return html;
}

function initRoomChangeData(){
    var codes = ["${hfdh}","${hfrq}","${hflx}","${khxm}","${lxfs}","${zjh}","${lxdz}",
        "${yfymc}","${yxmmc}","${yzqymc}","${yloudong}","${ydanyuan}","${yfanghao}","${yfydj}","${yfyzj}","${yfyzj_rmb}",
        "${yfyzk}","${yfsf}","${yfsfze}","${yfsfze_rmb}","${yrgze}","${yrgze_rmb}",
        "${fymc}","${xmmc}","${zqymc}","${loudong}","${danyuan}","${fanghao}","${fydj}","${fyzj}","${fyzj_rmb}","${yhfa}",
        "${zk}","${fsf}","${fsfze}","${fsfze_rmb}","${rgze}","${rgze_rmb}","${fkfs}","${dkyh}","${gjjdkje}","${gjjdkje_rmb}","${sydkje}","${sydkje_rmb}",
        "${dkze}","${dkze_rmb}","${yjje}","${yjje_rmb}","${hfyy}","${zygw}","${zdr}"];

    var names = ["单号","换房日期","换房类型","客户姓名","联系方式","证件号","联系地址",
        "原房源名称","原项目名称","原子区域名称","原楼栋","原单元","原房号","原房源单价","原房源总价","原房源总价(大写)",
        "原房源折扣","原附属房","原附属房总额","原附属房总额(大写)","原认购总额","原认购总额(大写)",
        "新房源名称","项目名称","子区域名称","楼栋","单元","房号","新房源单价","新房源总价","新房源总价(大写)","新优惠方案",
        "新房源折扣","附属房","新附属房总额","新附属房总额(大写)","新认购总额","新认购总额(大写)","新付款方式","新贷款银行","公积金贷款金额","公积金贷款金额(大写)","商业贷款金额","商业贷款金额(大写)",
        "贷款总额","贷款总额(大写)","单据已交金额","单据已交金额(大写)","换房原因","置业顾问","制单人"];
    var html = "<div style='overflow-y: auto;height: 500px;' class='list_table'><table style='width: 90%;'>";
    for(var i=0;i<codes.length;i++){
        html += '<tr><td style="width:35%;text-align: center;vertical-align: middle;">'+names[i]+'</td><td style="padding:1px;"><input class="form-control" style="width: 100%;cursor: pointer;font-size: 14px;" value="'+codes[i]+'" onclick="copyContent(this);" readonly/></td></tr>';
    }
    html+= '</table></div>';
    return html;
}
/**
 * 暂时不做
 * @returns {string}
 */
function initPayModeChangeData(){
    var codes = ["${bgdh}","${bgrq}","${khxm}","${lxfs}","${zjh}","${lxfs}","${fymc}","${xsmj}","${fytnmj}","",
        "${bdj}","${bzj}","${dj}","${zj}","${fsf}","${fsfze}","${fsfze_rmb}","${djze}","${djze_rmb}","${zk}",
        "${yhfa}","${zkbp}","${fkfs}","${dkyh}","${gjjdkje}","${sydkje}","${dkze}","${fkjh}","${ydj}","${yzj}",
        "${yfsf}","${yjdze}","${yzk}","${yyhfa}","${yzkbp}","${yfkfs}","${ydkyh}","${ygjjdkje}","${ysydkje}","${ydkze}",
        "${yfkjh}"];

    var names = ["变更单号","变更日期","客户姓名","联系方式","证件号","联系地址","房源名称","销售面积","套内面积","",
        "表单价","表总价","新单价","新总价","新附属房","附属房总额","附属房总额(大写)","新单据总价","新单据总价(大写)","新折扣",
        "新优惠方案","新折扣报批单","新付款方式","新贷款银行","新公积金贷款","新商业贷款","新贷款总额","付款计划","原单价","原总价",
        "原附属房","原单据总价","原折扣","原优惠方案","原折扣报批","原付款方式","原贷款银行","原公积金贷款","原商业贷款","原贷款总额",
        "原第一笔付款计划"];
    var html = "<div style='overflow-y: auto;height: 500px;' class='list_table'><table style='width: 90%;'>";
    for(var i=0;i<codes.length;i++){
        html += '<tr><td style="width:35%;text-align: center;vertical-align: middle;">'+names[i]+'</td><td style="padding:1px;"><input class="form-control" style="width: 100%;cursor: pointer;font-size: 14px;" value="'+codes[i]+'" onclick="copyContent(this);" readonly/></td></tr>';
    }
    html+= '</table></div>';
    return html;
}

function initRefundData(){
    var codes = ["${djh}","${tfrq}","${tflx}",
        "${khxm}","${lxfs}","${zjh}","${lxdz}",
        "${fymc}","${xmmc}","${zqymc}","${loudong}","${danyuan}","${fanghao}","${fyxsmj}","${fytnmj}","${fyhx}",
        "${djrq}","${dj}","${zj}","${zj_rmb}","${ytje}","${ytje_rmb}","${tfyy}","${zygw}","${zdr}",
        "${fsf}","${fsfze}","${fsfze_rmb}",
        "${djze} ","${djze_rmb}",
        "${cw_mc}","${cw_mj}","${cw_dj}","${cw_zj}","${cw_ts}","${cw_zmj}","${cw_hj}","${cw_hj_rmb}",
        "${ccs_mc}","${ccs_mj}","${ccs_dj}","${ccs_zj}","${ccs_ts}","${ccs_zmj}","${ccs_hj}","${ccs_hj_rmb}",
        "${fsf_mc}","${fsf_mj}","${fsf_dj}","${fsf_zj}","${fsf_ts}","${fsf_zmj}","${fsfze}","${fsfze_rmb}","${fsf}"];

    var names = ["单据号","退房日期","退房类型",
        "客户名称","联系方式","证件号","联系地址",
        "房源名称","项目名称","子区域名称","楼栋","单元","房号","销售面积","套内面积","户型",
        "单据签订日期","单价","总价","总价(大写)","应退金额","应退金额(大写)","退房原因","置业顾问","制单人",
        "附属房","附属房总额","附属房总额(大写)",
        "单据总额","单据总额(大写)",
        "车位名称","车位面积","车位单价","车位总价","车位套数","车位总面积","车位合计总额","车位合计总额(大写)",
        "储藏室名称","储藏室面积","储藏室单价","储藏室总价","储藏室套数","储藏室总面积","储藏室合计总额","储藏室合计总额(大写)",
        "附属房名称","附属房面积","附属房单价","附属房总价","附属房套数","附属房总面积","附属房总额","附属房总额(大写)","附属房列表"];
    var html = "<div style='overflow-y: auto;height: 500px;' class='list_table'><table style='width: 90%;'>";
    for(var i=0;i<codes.length;i++){
        html += '<tr><td style="width:35%;text-align: center;vertical-align: middle;">'+names[i]+'</td><td style="padding:1px;"><input class="form-control" style="width: 100%;cursor: pointer;font-size: 14px;" value="'+codes[i]+'" onclick="copyContent(this);" readonly/></td></tr>';
    }
    html+= '</table></div>';
    return html;
}

function initClientChangeData(){
    var codes = ["${djh}","${bgrq}","${bglx}","${bgyy}","${zygw}","${zdr}",
        "${ykhxm}","${yzjh}","${ylxfs}","${ylxdz}","${ygtqyr}",
        "${khxm}","${lxfs}","${zjh}","${lxdz}","${gtqyr}",
        "${fymc}","${xmmc}","${zqymc}","${loudong}","${danyuan}","${fanghao}"];

    var names = ["单据号","变更日期","变更类型","变更原因","置业顾问","制单人",
        "原客户姓名","原证件号","原联系方式","原联系地址","原共同权益人",
        "新客户姓名","新联系方式","新证件号","新联系地址","新共同权益人",
        "房源名称","项目名称","子区域名称","楼栋","单元","房号"];
    var html = "<div style='overflow-y: auto;height: 500px;' class='list_table'><table style='width: 90%;'>";
    for(var i=0;i<codes.length;i++){
        html += '<tr><td style="width:35%;text-align: center;vertical-align: middle;">'+names[i]+'</td><td style="padding:1px;"><input class="form-control" style="width: 100%;cursor: pointer;font-size: 14px;" value="'+codes[i]+'" onclick="copyContent(this);" readonly/></td></tr>';
    }
    html+= '</table></div>';
    return html;
}

function initDiscountData(){
    var codes = ["${bprq}","${fymc}","${xmmc}","${zqymc}","${loudong}","${danyuan}","${fanghao}","${fyjzmj}","${fytnmj}", "${fyhx}",
        "${khxm}","${lxfs}","${zjh}","${lxdz}","${yhfa}","${bdj}","${bzj}","${bzj_rmb}","${yhdj}","${yhzj}","${yhzj_rmb}",
        "${bpdj}","${bpzj}","${bpzj_rmb}","${zk}","${beizhu}","${zygw}"];

    var names = ["日期","房源名称","项目名称","子区域名称","楼栋","单元","房号","建筑面积","套内面积","户型",
        "客户名称","联系方式","证件号","联系地址","优惠方案","表单价","表总价","表总价(大写)","优惠后单价","优惠后总价","优惠后总价(大写)",
        "折扣后单价","折扣后总价","折扣后总价(大写)","折扣点","备注","置业顾问"];
    var html = "<div style='overflow-y: auto;height: 500px;' class='list_table'><table style='width: 90%;'>";
    for(var i=0;i<codes.length;i++){
        html += '<tr><td style="width:35%;text-align: center;vertical-align: middle;">'+names[i]+'</td><td style="padding:1px;"><input class="form-control" style="width: 100%;cursor: pointer;font-size: 14px;" value="'+codes[i]+'" onclick="copyContent(this);" readonly/></td></tr>';
    }
    html+= '</table></div>';
    return html;
}

function initVipData(){
    var codes = ["${kahao}","${khxm}","${lxfs}","${zjh}","${lxdz}","${fkrq}","${sxrq}","${pch}","${yjje}","${yjje_rmb}","${ysje}","${ysje_rmb}","${beizhu}","${yxfy}","${zygw}"];
    var names = ["卡号","客户姓名","联系方式","证件号","联系地址","发卡日期","失效日期","批次号","应交金额","应交金额(大写)","已收金额","已收金额(大写)","备注","意向房源","置业顾问"];
    var html = "<div style='overflow-y: auto;height: 500px;' class='list_table'><table style='width: 90%;'>";
    for(var i=0;i<codes.length;i++){
        html += '<tr><td style="width:35%;text-align: center;vertical-align: middle;">'+names[i]+'</td><td style="padding:1px;"><input class="form-control" style="width: 100%;cursor: pointer;font-size: 14px;" value="'+codes[i]+'" onclick="copyContent(this);" readonly/></td></tr>';
    }
    html+= '</table></div>';
    return html;
}

function initShouJuData(){
    var codes = ["${companyName}","${receiptNo}","${receiptDate}","${receiptMode}","${rmb}","${rmbUpper}","${remarkType}","${remark}","${cashier}","${operator}"];
    var names = ["交款单位(或姓名)","收据号","收据日期","收款方式","人民币","人民币大写","备注事项","备注","出纳","经办人"];
    var html = "<div style='overflow-y: auto;height: 500px;' class='list_table'><table style='width: 90%;'>";
    for(var i=0;i<codes.length;i++){
        html += '<tr><td style="width:35%;text-align: center;vertical-align: middle;">'+names[i]+'</td><td style="padding:1px;"><input class="form-control" style="width: 100%;cursor: pointer;font-size: 14px;" value="'+codes[i]+'" onclick="copyContent(this);" readonly/></td></tr>';
    }
    html+= '</table></div>';
    return html;
}

function copyContent(obj){
    obj.select(); // 选择文本
    document.execCommand("Copy"); // 执行浏览器复制命令
}
