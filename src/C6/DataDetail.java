package C6;

import java.util.BitSet;
/**
 * 
 * @author 贾新禹
 * 为每一个数据项的详细内容,列表用BitSet存储，只能存入int型的商品ID
 * 如果商品ID过大，可以利用hashMap做次标号
 * @version 0.0.1
 *
 */
public class DataDetail {
	@SuppressWarnings("unused")
	private int TID;
	private BitSet detail;
	public DataDetail()
	{
		this(0,new BitSet());
	}
	public DataDetail(int tid)
	{
		this(tid,new BitSet());
	}
	/**
	 * 构造函数
	 * @param tid TID事务编号
	 * @param dList 商品的BitSet，所有存在的商品ID设为true
	 */
	public DataDetail(int tid,BitSet dList)
	{
		TID=tid;
		detail=dList;
	}
	public void add(int s)
	{
		 detail.set(s, true);
	}
	public void del(int s)
	{
		detail.set(s, false);
	}
	public BitSet set()
	{
		return detail;
	}
}
