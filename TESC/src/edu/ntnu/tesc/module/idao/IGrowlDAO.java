package edu.ntnu.tesc.module.idao;

import java.util.List;

import edu.ntnu.tesc.module.beans.Growl;

public interface IGrowlDAO {
	public List<Growl> getGrowlList();
    public Growl getGrowl(int autoindex);
    public int insertGrowl(Growl growl);
    public int updateGrowl(Growl growl);
    public int delGrowl(int autoindex);
}
