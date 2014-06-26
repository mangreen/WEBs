package edu.ntnu.tesc.module.idao;

import java.util.List;

import edu.ntnu.tesc.module.beans.Qeii;

public interface IQeiiDAO {
	public List<Qeii> getQeiiList();
    public Qeii getQeii(int autoindex);
    public int insertQeii(Qeii qeii);
    public int updateQeii(Qeii qeii);
    public int delQeii(int autoindex);
}
