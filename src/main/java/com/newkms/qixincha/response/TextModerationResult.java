package com.newkms.qixincha.response;

import java.io.Serializable;
import java.util.List;

/**
 * 文本审核结果
 */
public class TextModerationResult extends ApiResult implements Serializable {
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
     * 文本命中的关键词列表
     */
    private List<MatchedList> matchedList;

    /**
     * 命中的所有违规标签以及其详情信息
     */
    private List<TextAllTag> allTags;

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

    public List<MatchedList> getMatchedList() {
        return matchedList;
    }

    public void setMatchedList(List<MatchedList> matchedList) {
        this.matchedList = matchedList;
    }

    public List<TextAllTag> getAllTags() {
        return allTags;
    }

    public void setAllTags(List<TextAllTag> allTags) {
        this.allTags = allTags;
    }


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

    public static class TextAllTag {
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
         * 该段文本命中关键词时存在，字段详情参考外层matchedList
         */
        private List<MatchedList> matchedList;

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

        public List<MatchedList> getMatchedList() {
            return matchedList;
        }

        public void setMatchedList(List<MatchedList> matchedList) {
            this.matchedList = matchedList;
        }

        @Override
        public String toString() {
            return "TextAllTag{" + "machineSuggestion='" + machineSuggestion + '\'' + ", machineTagL1='" + machineTagL1 + '\'' + ", machineTagL2='" + machineTagL2 + '\'' + ", confidence=" + confidence + ", matchedList=" + matchedList + '}';
        }
    }

    @Override
    public String toString() {
        return "TextModerationResult{" +
                "code=" + this.getCode() +
                ", msg='" + this.getMessage() + '\'' +
                ", machineSuggestion=" + machineSuggestion +
                ", machineTagL1='" + machineTagL1 + '\'' +
                ", machineTagL2='" + machineTagL2 + '\'' +
                ", contentId='" + contentId + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                ", matchedList=" + matchedList +
                ", allTags=" + allTags +
                '}';
    }
}
