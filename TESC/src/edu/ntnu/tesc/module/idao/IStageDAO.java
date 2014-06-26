package edu.ntnu.tesc.module.idao;

import java.util.List;

import edu.ntnu.tesc.module.beans.Stage;

public interface IStageDAO {
    public List<Stage> getStageList();
    public Stage getStage(int autoindex);
    public int insertStage(Stage stage);
    public int updateStage(Stage stage);
    public int delStage(int autoindex);
}
