package edu.ntnu.tesc.module.idao;

import java.util.List;

import edu.ntnu.tesc.module.beans.QeiiFile;

public interface IQeiiFileDAO {
	public List<QeiiFile> getQeiiFileList();
    public QeiiFile getQeiiFile(int autoindex);
    public int insertQeiiFile(QeiiFile qeiiFile);
    public int updateQeiiFile(QeiiFile qeiiFile);
    public int delQeiiFile(int autoindex);
}
