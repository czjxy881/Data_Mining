package C8;

import java.util.HashMap;
import java.util.Set;

/**
 * 类别类，Name为列名，ID为对应的编号用于数据紧密存储，IsCategoriacl是否离散
 * @author jxy
 *
 */

public class Key {
	private String Name;
	private int ID;
	private boolean IsCategorical;//是否离散
	private HashMap<String, Integer> Values;
	private HashMap<Integer,Integer> Count; 
	private int CountAll;
	public Key(String name,int id,boolean IsC)
	{
		this(name, id, IsC,null);
	}
	/**
	 * 构造函数，如果IsC为false，vs强制为null
	 * @param name 列名
	 * @param id 编号
	 * @param IsC 是否离散
	 * @param vs 可取的集合,只有初始化时可以赋值
	 */
	public Key(String name,int id,boolean IsC,String []vs)
	{
		Name=name;
		ID=id;
		IsCategorical=IsC;
		if(IsC==false)vs=null;
		Values=new HashMap<String,Integer>();
		Count=new HashMap<Integer,Integer>();
		add_value(vs);
		CountAll=0;
	}
	private void add_value(String[] vs)
	{
		if(vs==null)return;
		for(String v:vs)
		{
			add_value(v);
		}
	}
	private void add_value(String v)
	{
		if(Values.containsKey(v))return;
		Values.put(v, Values.size()+1);
		Count.put(Values.size(), 0);
	}
	public String get_name()
	{
		return Name;
	}
	public int get_id()
	{
		return ID;
	}
	public int get_value(String v)
	{
		return Values.get(v);
	}
	public boolean isCategorical()
	{
		return IsCategorical;
	}
	public void add_Count(int i)
	{
		Count.put(i, Count.get(i)+1);
		CountAll++;
	}
	public int get_Count(String s)
	{
		return Count.get(Values.get(s));
	}
	public int get_SUM()
	{
		return CountAll;
	}
	public Set<String> get_Values()
	{
		return Values.keySet();
	}
}
