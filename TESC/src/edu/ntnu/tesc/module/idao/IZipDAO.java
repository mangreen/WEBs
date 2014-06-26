package edu.ntnu.tesc.module.idao;

import java.util.List;

import edu.ntnu.tesc.module.beans.Zip;

public interface IZipDAO {
	public List<Zip> getZipList();
    public Zip getZip(int zip_id);
    public int insertZip(Zip zip);
    public int updateZip(Zip zip);
    public int delZip(int zip_id);
}
