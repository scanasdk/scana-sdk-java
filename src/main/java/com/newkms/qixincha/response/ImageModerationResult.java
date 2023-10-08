package com.newkms.qixincha.response;

import java.io.Serializable;
import java.util.List;

/**
 * 图片审核结果
 */
public class ImageModerationResult extends ApiResult implements Serializable {
    /**
     * 机审建议:1正常，2疑似违规，3违规
     */
    private Integer machineSuggestion;
    /**
     * 机审一级标签，当machineSuggestion为1时，返回”正常“
     */
    private String machineTagL1;
    /**
     * 机审二级标签，当machineSuggestion为1时，为空
     */
    private String machineTagL2;
    /**
     * 审核时传入的contentId
     */
    private String contentId;
    /**
     * 该条数据对应的请求标识,由ScanA生成
     */
    private String uniqueId;

    /**
     * 违规风险信息
     */
    private ImageModerationRiskDetail riskDetails;

    /**
     * 命中的所有违规标签以及其详情信息
     */
    private List<ImageAllTag> allTags;


    public Integer getMachineSuggestion() {
        return machineSuggestion;
    }

    public void setMachineSuggestion(Integer machineSuggestion) {
        this.machineSuggestion = machineSuggestion;
    }

    public String getMachineTagL1() {
        return machineTagL1;
    }

    public void setMachineTagL1(String machineTagL1) {
        this.machineTagL1 = machineTagL1;
    }

    public String getMachineTagL2() {
        return machineTagL2;
    }

    public void setMachineTagL2(String machineTagL2) {
        this.machineTagL2 = machineTagL2;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public ImageModerationRiskDetail getRiskDetails() {
        return riskDetails;
    }

    public void setRiskDetails(ImageModerationRiskDetail riskDetails) {
        this.riskDetails = riskDetails;
    }

    public List<ImageAllTag> getAllTags() {
        return allTags;
    }

    public void setAllTags(List<ImageAllTag> allTags) {
        this.allTags = allTags;
    }

    /**
     * 图片违规风险信息
     */
    public static class ImageModerationRiskDetail {
        /**
         * 返图片中敏感人物的名称及位置信息
         */
        private List<ImageModerationRiskDetailFace> faces;
        /**
         * 图片中违规文字相关信息
         */
        private List<ImageModerationRiskDetailOcr> ocrText;
        /**
         * 物体信息，返回图片中违规物体信息
         */
        private List<ImageModerationRiskDetailObject> objects;
        /**
         * 标识违规点来源，1: 无风险，2: 文字风险，3: 图片视觉风险
         */
        private Integer riskSource;

        public List<ImageModerationRiskDetailFace> getFaces() {
            return faces;
        }

        public void setFaces(List<ImageModerationRiskDetailFace> faces) {
            this.faces = faces;
        }

        public List<ImageModerationRiskDetailOcr> getOcrText() {
            return ocrText;
        }

        public void setOcrText(List<ImageModerationRiskDetailOcr> ocrText) {
            this.ocrText = ocrText;
        }

        public List<ImageModerationRiskDetailObject> getObjects() {
            return objects;
        }

        public void setObjects(List<ImageModerationRiskDetailObject> objects) {
            this.objects = objects;
        }

        public Integer getRiskSource() {
            return riskSource;
        }

        public void setRiskSource(Integer riskSource) {
            this.riskSource = riskSource;
        }

        @Override
        public String toString() {
            return "ImageModerationRiskDetail{" +
                    "faces=" + faces +
                    ", ocrText=" + ocrText +
                    ", objects=" + objects +
                    ", riskSource=" + riskSource +
                    '}';
        }
    }

    /**
     * 人脸命中信息
     */
    public static class ImageModerationRiskDetailFace {
        /**
         * 该人脸在此图上的id编号，如果同一个人脸在图片中出现n次，则会有多个id
         */
        private Integer id;
        /**
         * 人脸框坐标。该数组有四个值，分别代表目标框左上角和右下角的坐标。
         * 如[33,44,55,66]33代表的是左上角的x坐标,44代表左上角的y坐标,55代表的是右下角的x坐标,
         * 66代表的是右下角的y坐标
         */
        private List<Float> location;
        /**
         * 人物名称
         */
        private String name;
        /**
         * 人脸大小在整张图片的占比，0到1之间，值越高，说明人脸越大，更加值得关注
         */
        private Float ratio;
        /**
         * 置信度，0到1之间，值越高，可信度越高
         */
        private Float confidence;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<Float> getLocation() {
            return location;
        }

        public void setLocation(List<Float> location) {
            this.location = location;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Float getRatio() {
            return ratio;
        }

        public void setRatio(Float ratio) {
            this.ratio = ratio;
        }

        public Float getConfidence() {
            return confidence;
        }

        public void setConfidence(Float confidence) {
            this.confidence = confidence;
        }

        @Override
        public String toString() {
            return "ImageModerationRiskDetailFace{" +
                    "id=" + id +
                    ", location=" + location +
                    ", name='" + name + '\'' +
                    ", ratio=" + ratio +
                    ", confidence=" + confidence +
                    '}';
        }
    }

    /**
     * ocr 命中信息
     */
    public static class ImageModerationRiskDetailOcr {
        /**
         * 文本框坐标。该数组有八个值，分别代表文本框四个顶点的x和y坐标
         */
        private List<Float> location;
        /**
         * 该段文本
         */
        private String text;
        /**
         * 当该段文本命中关键词时存在
         */
        private List<MatchedList> matchList;
        /**
         * 当该段文本命中语义模型时存在
         */
        private List<ImageModerationModelRisk> modelRiskList;

        public List<Float> getLocation() {
            return location;
        }

        public void setLocation(List<Float> location) {
            this.location = location;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<MatchedList> getMatchList() {
            return matchList;
        }

        public void setMatchList(List<MatchedList> matchList) {
            this.matchList = matchList;
        }

        public List<ImageModerationModelRisk> getModelRiskList() {
            return modelRiskList;
        }

        public void setModelRiskList(List<ImageModerationModelRisk> modelRiskList) {
            this.modelRiskList = modelRiskList;
        }

        @Override
        public String toString() {
            return "ImageModerationRiskDetailOcr{" +
                    "location=" + location +
                    ", text='" + text + '\'' +
                    ", matchList=" + matchList +
                    ", modelRiskList=" + modelRiskList +
                    '}';
        }
    }


    /**
     * 目标检测命中信息
     */
    public static class ImageModerationRiskDetailObject {
        /**
         * 该物体在此图上的id编号
         */
        private Integer id;
        /**
         * 人脸框坐标。该数组有四个值，分别代表目标框左上角和右下角的坐标。
         * 如[33,44,55,66]33代表的是左上角的x坐标,44代表左上角的y坐标,55代表的是右下角的x坐标,
         * 66代表的是右下角的y坐标
         */
        private List<Float> location;
        /**
         * 违规标签
         */
        private String name;
        /**
         * 置信度，0到1之间，值越高，可信度越高
         */
        private Float confidence;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<Float> getLocation() {
            return location;
        }

        public void setLocation(List<Float> location) {
            this.location = location;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Float getConfidence() {
            return confidence;
        }

        public void setConfidence(Float confidence) {
            this.confidence = confidence;
        }

        @Override
        public String toString() {
            return "ImageModerationRiskDetailObject{" +
                    "id=" + id +
                    ", location=" + location +
                    ", name='" + name + '\'' +
                    ", confidence=" + confidence +
                    '}';
        }
    }

    public static class ImageModerationModelRisk {
        /**
         * 命中语义模型类别结果，如“涉政:敏感事件”（一级二级标签拼接）
         */
        private String description;
        /**
         * 置信度，0到1之间，值越高，可信度越高
         */
        private Float confidence;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Float getConfidence() {
            return confidence;
        }

        public void setConfidence(Float confidence) {
            this.confidence = confidence;
        }

        @Override
        public String toString() {
            return "ImageModerationModelRisk{" +
                    "description='" + description + '\'' +
                    ", confidence=" + confidence +
                    '}';
        }
    }

    /**
     * 关键词匹配信息
     */
    public static class MatchedList {
        /**
         * 命中的关键词
         */
        private String keyword;
        /**
         * 该敏感词的一级二级标签拼接，如“涉政:领导人”
         */
        private String tag;
        /**
         * 关键词其他描述
         */
        private String description;
        /**
         * 该敏感词在此段文本中的启止位置。如[3,5]，代表该文段的第3个字符和第4个字符命中了关键词
         */
        private List<Integer> position;

        public String getKeyword() {
            return keyword;
        }


        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Integer> getPosition() {
            return position;
        }

        public void setPosition(List<Integer> position) {
            this.position = position;
        }


        @Override
        public String toString() {
            return "MatchedList{" + "keyword='" + keyword + '\'' + ", tag='" + tag + '\'' + ", description='" + description + '\'' + ", position=" + position + '}';
        }
    }

    /**
     * 图片所有违规标签信息
     * 违规可能会命中多种违规，最终违规信息参考外层
     */
    public static class ImageAllTag {
        /*
        机审建议:1正常，2疑似违规，3违规
         */
        private String machineSuggestion;

        /**
         * 一级标签
         */
        private String machineTagL1;
        /**
         * 二级标签
         */
        private String machineTagL2;
        /**
         * 置信度，值在0到1之间，值越大，违规可能性越高。
         */
        private Float confidence;
        /**
         * 当前违规类别的风险详情
         */
        private ImageModerationRiskDetail details;

        public String getMachineSuggestion() {
            return machineSuggestion;
        }

        public void setMachineSuggestion(String machineSuggestion) {
            this.machineSuggestion = machineSuggestion;
        }

        public String getMachineTagL1() {
            return machineTagL1;
        }

        public void setMachineTagL1(String machineTagL1) {
            this.machineTagL1 = machineTagL1;
        }

        public String getMachineTagL2() {
            return machineTagL2;
        }

        public void setMachineTagL2(String machineTagL2) {
            this.machineTagL2 = machineTagL2;
        }

        public Float getConfidence() {
            return confidence;
        }

        public void setConfidence(Float confidence) {
            this.confidence = confidence;
        }

        public ImageModerationRiskDetail getDetails() {
            return details;
        }

        public void setDetails(ImageModerationRiskDetail details) {
            this.details = details;
        }

        @Override
        public String toString() {
            return "ImageAllTag{" +
                    "machineSuggestion='" + machineSuggestion + '\'' +
                    ", machineTagL1='" + machineTagL1 + '\'' +
                    ", machineTagL2='" + machineTagL2 + '\'' +
                    ", confidence=" + confidence +
                    ", details=" + details +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ImageModerationResult{" +
                "code=" + this.getCode() +
                ", msg='" + this.getMessage() + '\'' +
                ", machineSuggestion=" + machineSuggestion +
                ", machineTagL1='" + machineTagL1 + '\'' +
                ", machineTagL2='" + machineTagL2 + '\'' +
                ", contentId='" + contentId + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                ", riskDetails=" + riskDetails +
                ", allTags=" + allTags +
                '}';
    }
}
