package C6;

import java.util.ArrayList;

/**
 * 数据集，有独有自增ID，和多个事务项
 * 注意事务项是BitSet，如果商品ID过大，应该在这一层设置HashMap进行标号
 * @author jxy
 * 
 */
public class DataBase {
	/**
	 * 数据集合
	 */
	private ArrayList<DataDetail> dataList;
	/**
	 * 最大的列号
	 */
	private int MAX_ID;
	public DataBase()
	{
		dataList=new ArrayList<DataDetail>();
		MAX_ID=0;
	}
	/**
	 * 增加一行数据
	 * @param s DataDetail形式的数据项
	 * @return 是否成功
	 */
	public boolean add(DataDetail s)
	{
		//TODO:这边权责没有分配好，不应该把预处理权责给DataDetail，DateDetail应该对用户不可见
		MAX_ID=Math.max(MAX_ID,s.set().length()-1);
		return dataList.add(s);
	}
	/**
	 * 删除一行数据
	 * @param s 数据项
	 * @return 是否成功
	 */
	public boolean del(DataDetail s)
	{
		return dataList.remove(s);
	}
	/**
	 * 返回数据项个数，即有几行
	 * @return 数据项个数
	 */
	public int size()
	{
		return dataList.size();
	}
	/**
	 * 返回最大的商品ID，获得一共有几列
	 * @return 最大商品ID
	 */
	public int maxID()
	{
		return MAX_ID;
	}
	/**
	 * 获得详细数据集，这个其实不应该是Public的
	 * @return 数据集
	 */
	public ArrayList<DataDetail> list()
	{
		return dataList;
	}
}
