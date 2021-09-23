package cn.smart.bo;

import java.util.List;

public class WeekReportGameBO {

    private String suggestions;

    private List<WeekReportWordBO> wordList;

    private String suggestionVoice;

    public List<WeekReportWordBO> getWordList() {
        return wordList;
    }

    public void setWordList(List<WeekReportWordBO> wordList) {
        this.wordList = wordList;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public String getSuggestionVoice() {
        return suggestionVoice;
    }

    public void setSuggestionVoice(String suggestionVoice) {
        this.suggestionVoice = suggestionVoice;
    }
}
