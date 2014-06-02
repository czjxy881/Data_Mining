package C6;

import java.util.ArrayList;

/**
 * 
 * @author jxy
 * 数据集，有独有自增ID，和多个事务项
 * 注意事务项是BitSet，如果商品ID过大，应该在这一层设置HashMap进行标号
 */

public class DataBase {
	private ArrayList<DataDetail> dataList;
	private static int DID=0;
	@SuppressWarnings("unused")
	private int ID;
	private int MAX_ID;
	public DataBase()
	{
		ID=DID++;
		dataList=new ArrayList<DataDetail>();
		MAX_ID=0;
	}
	public boolean add(DataDetail s)
	{
		
		MAX_ID=Math.max(MAX_ID,s.set().length()-1);
		return dataList.add(s);
	}
	public boolean del(DataDetail s)
	{
		return dataList.remove(s);
	}
	public int size()
	{
		return dataList.size();
	}
	public int maxID()
	{
		return MAX_ID;
	}
	public ArrayList<DataDetail> list()
	{
		return dataList;
	}
}
