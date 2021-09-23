package cn.smart.bo;

import java.util.List;

public class LearnDetailBO {

    private List<LearnDetailInfoBO> learnInfos;
    private List<GameInfoBO> gameLists;
    private List<GameInfoBO> listSpeakUp;

    public List<LearnDetailInfoBO> getLearnInfos() {
        return learnInfos;
    }

    public void setLearnInfos(List<LearnDetailInfoBO> learnInfos) {
        this.learnInfos = learnInfos;
    }

    public List<GameInfoBO> getGameLists() {
        return gameLists;
    }

    public void setGameLists(List<GameInfoBO> gameLists) {
        this.gameLists = gameLists;
    }

    public List<GameInfoBO> getListSpeakUp() {
        return listSpeakUp;
    }

    public void setListSpeakUp(List<GameInfoBO> listSpeakUp) {
        this.listSpeakUp = listSpeakUp;
    }
}
