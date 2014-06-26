package edu.ntnu.tesc.module.idao;

import java.util.List;

import edu.ntnu.tesc.module.beans.QeisBudgetYear;

public interface IQeisBudgetYearDAO {

	public List<QeisBudgetYear> getQeisBudgetYearList();
    public QeisBudgetYear getQeisBudgetYear(int autoindex);
    public int insertQeisBudgetYear(QeisBudgetYear qeisBudgetYear);
    public int updateQeisBudgetYear(QeisBudgetYear qeisBudgetYear);
    public int delQeisBudgetYear(int autoindex);
}
