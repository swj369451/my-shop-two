/**
*
 默认校验规则说明,加在input的class中
 required：true 必输字段
 remote：check.php 使用 ajax 方法调用 check.php 验证输入值
 email：true 必须输入正确格式的电子邮件
 url：true 必须输入正确格式的网址
 date：true 必须输入正确格式的日期
 dateISO：true 必须输入正确格式的日期(ISO)，例如：2009-06-23，1998/01/22 只验证格式，不验证有效性
 number：true 必须输入合法的数字(负数，小数)
 digits：true 必须输入整数
 creditcard： 必须输入合法的信用卡号
 equalTo：#field，输入值必须和 #field 相同
 accept： 输入拥有合法后缀名的字符串（上传文件的后缀）
 maxlength：5，输入长度最多是5的字符串(汉字算一个字符)
 minlength：10，输入长度最小是10的字符串(汉字算一个字符)
 rangelength：[5,10]，输入长度必须介于 5 和 10 之间的字符串")(汉字算一个字符)
 range：[5,10]，输入值必须介于 5 和 10 之间
 max：5，输入值不能大于 5
 min：10，输入值不能小于 10
*/
/**
 * Jquery Validate
 *
 */
var Validate = function () {
    /**
     * 定义一个函数，初始化jQuery validation
     * 这里定义的都是私有的
     */
    /**
     * 初始化Jquery Validate
     */
    var handlerInitValidate = function () {


        $("#inputForm").validate({
            errorElement: 'span',
            errorClass: 'help-block',
            errorPlacement: function (error, element) {
                element.parent().parent().attr("class", "form-group has-error");
                error.insertAfter(element);
            }
        });
    };
    /**
     * 添加自定义的验证
     */
    var handlerInitCustomValidate = function () {
        $.validator.addMethod("mobile", function(value, element) {
            var length = value.length;
            var mobile =  /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");
    };
    /**
     * return里面的是公共的方法，外面可以访问的
     * {}是对象，所以里面只有属性
     */
    return{
        init:function () {
            handlerInitCustomValidate();
            handlerInitValidate();
        }
    }
}();
/**
 * 初始化完文档执行
 */
$(document).ready(function () {
    Validate.init();
});