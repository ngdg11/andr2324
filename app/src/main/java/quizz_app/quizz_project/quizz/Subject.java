package quizz_app.quizz_project.quizz;

public class Subject {
    private int subId;
    private String nameSub;
    private  String subDes;
    private int srcImgPosition;
    public Subject(int subId, String nameSub, String subDes, int srcImgPosition) {
        this.subId = subId;
        this.nameSub = nameSub;
        this.subDes = subDes;
        this.srcImgPosition = srcImgPosition;
    }
    public int getSrcImgPosition() {
        return srcImgPosition;
    }
    public int getSubId( ) {
        return subId;
    }

    public String getNameSub() {
        return nameSub;
    }

    public String getSubDes (){
        return subDes;
    }
    public void setSubId(int subId) {
        this.subId = subId;
    }
    public void setNameSub(String nameSub) {
        this.nameSub = nameSub;
    }

    public void setSubDes(String subDes){
        this.subDes = subDes;
    }
    public void setSrcImgPosition(int srcImgPosition) {
        this.srcImgPosition = srcImgPosition;
    }
}
