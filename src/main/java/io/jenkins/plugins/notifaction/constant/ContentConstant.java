package io.jenkins.plugins.notifaction.constant;

/**
 * 文本类型常量
 * @author 三月柳絮四月雨
 * @date 2020/11/05
 **/
public class ContentConstant {

    public static final String START_TEXT = "开始构建";
    public static final String END_TEXT = "构建结束";

    public static final String PROJECT_NAME = "项目:";
    public static final String DESCRIPTION = "描述:";
    public static final String BRANCH_NAME = "分支:";
    public static final String AUTHOR_NAME = "提交人:";
    public static final String COMMIT_MESSAGE = "提交信息:";
    public static final String ESTIMATED_DURATION = "估计时间:";
    public static final String RESULT = "结果:";

    public static final String WRAP = "\n";

    public static final String QUOTE = ">";

    public static final String GREEN_FONT_COLOR = "<font color=\"info\">";
    public static final String GRAY_FONT_COLOR = "<font color=\"comment\">";
    public static final String ORANGE_FONT_COLOR = "<font color=\"warning\">";
    public static final String END_TAG = "</font>";



    private ContentConstant() {}
}
