package C10;

import java.util.Vector;
/**
 * 图类，用于保存图
 * @author jxy
 *
 */
public class Graph {
	private int Num_Point,Num_Edge;
	/**
	 * Connect正数为图中的边，负数为已删除的边，0代表无边便于算Q
	 * 邻接矩阵，过大时可换成邻接表 实际存储0开始，输入数据为1开始
	 */
	private int[][] Connect;
	/**
	 * 染色标号，用于染色分区
	 */
	private int[] Colors;
	/**
	 * 边的集合，用于枚举边，如用邻接表则不需要
	 */
	private Vector<int[]> Edges;
	/**
	 * 色块个数
	 */
	private int Color;
	/**
	 * 每个色块的出入度
	 */
	private Vector<int[]> NumC;
	/**
	 * 建图
	 * @param N 顶点数
	 */
	public Graph(int N) {
		Num_Point=N;
		Num_Edge=0;
		Connect=new int[N+1][N+1];
		Edges=new Vector<int[]>();
		NumC=new Vector<int[]>();
		int i,j;
		for(i=0;i<=N;i++)
			for(j=i;j<=N;j++)
			{
				Connect[i][j]=Connect[j][i]=0;
			}
	}
	/**
	 * 增加双向边，权值为1
	 * @param v 一个顶点
	 * @param u 另一个顶点
	 * @return 是否增加成功
	 */
	public boolean add_Edge(int v,int u){ 
		return add_Edge(v, u,1);
	}
	/**
	 * 
	 * 增加双向边，权值为c
	 * @param v 一个顶点
	 * @param u 另一个顶点
	 * @param c 权值
	 * @return 是否增加成功
	 */
	public boolean add_Edge(int v,int u,int c){ //无权值,双向边
		if(v<1||v>Num_Point||u<1||u>Num_Point)return false;
		v--;u--;
		if(Connect[v][u]==0)Num_Edge++;
		Connect[v][u]=Connect[u][v]=c;
		Edges.add(new int[]{u,v});
		return true;
	}
	/**
	 * 删除边，并从Edges中移除
	 * @param m Edges中标号
	 * @return 是否成功
	 */
	public boolean del_Edge(int m){
		if(Edges.size()<=m)return false;
		int v=Edges.get(m)[0],u=Edges.get(m)[1];
		
		Connect[v][u]=Connect[u][v]=-Connect[u][v];
		Num_Edge--;
		Edges.remove(m);
		return true;
	}
	/**
	 * 测试删除边，不从Edges中移除
	 * @param m Edges中标号
	 * @return 是否成功
	 */
	public boolean test_del(int m){
		if(Edges.size()<=m)return false;
		int v=Edges.get(m)[0],u=Edges.get(m)[1];
		Connect[v][u]=Connect[u][v]=-Connect[u][v];
		Num_Edge--;
		return true;
	}
	/**
	 * 恢复边
	 * @param m Edges中标号
	 * @return 是否成功
	 */
	public boolean resume_test(int m){
		if(Edges.size()<=m)return false;
		int v=Edges.get(m)[0],u=Edges.get(m)[1];
		Connect[v][u]=Connect[u][v]=-Connect[u][v];
		Num_Edge++;
		return true;
	}
	
	/**
	 * 求删除e边后的信息中心度 m*n^3
	 * @param e Edges中标号
	 * @return 信息中心度
	 */
	private double get_Edel(int e){
		int dis[][]=new int[Num_Point][Num_Point];
		int INF=10000000;
		int v=Edges.get(e)[0],u=Edges.get(e)[1];
		Connect[v][u]=Connect[u][v]=-Connect[u][v];
		
		for(int i=0;i<Num_Point;i++) //初始化距离
			for(int j=0;j<Num_Point;j++)
				if(Connect[i][j]>0)dis[i][j]=Connect[i][j];
				else dis[i][j]=INF;
		
		
		for(int k=0;k<Num_Point;k++) //floyd最短路
			for(int i=0;i<Num_Point;i++)
				for(int j=0;j<Num_Point;j++){
					if(dis[i][k]+dis[k][j]<dis[i][j]){
						dis[i][j]=dis[j][i]=dis[i][k]+dis[k][j];
					}
				}
		double sum=0;
		for(int i=0;i<Num_Point;i++) //根据公式计算
			for(int j=0;j<Num_Point;j++)
				if(dis[i][j]!=INF)
					sum+=1.0/(double)dis[i][j];
		Connect[v][u]=Connect[u][v]=-Connect[u][v];
		return sum;
	}
	/**
	 * 获得删去后信息中心性变化最大的边编号
	 * @return int 边编号
	 */
	public int get_E()
	{
		int ans=-1;
		double Min=20000;
		for(int e=0;e<Num_Edge;e++)
		{
			double t=get_Edel(e);
			if(Min>t){
				Min=t;
				ans=e;
			}
		}
		System.out.println(String.valueOf(get_Edel(3)/(Num_Point*(Num_Point-1))));
		System.out.println(String.valueOf(Edges.get(ans)[0]+1)+" "+String.valueOf(Edges.get(ans)[1]+1));
		return ans;
	}
	
	/*-----------
	 * Modularity 剪枝
	 */
	
	int in,out;
	/**
	 * 染色,并统计出入度
	 */
	public void paint(){ 
		Colors=new int[Num_Point+1];
		for(int i=0;i<Num_Point;i++)Colors[i]=0;
		Color=0;
		NumC.clear();
		NumC.add(null);
		for(int i=0;i<Num_Point;i++){
			if(Colors[i]==0){
				in=0;out=0;
				paint_one(i,++Color);
				NumC.add(new int[]{in/2,out});
			}
		}
	}
	/**
	 * dfs染色，统计这个点的内外边数
	 * @param v 当前点
	 * @param c 当前颜色
	 */
	private void paint_one(int v,int c) 
	{
		Colors[v]=c;
		for(int u=0;u<Num_Point;u++){
			if(Connect[v][u]<0)out++;
			else if(Connect[v][u]>0){
				if(Colors[u]!=c)paint_one(u, c);
				in++;
			}
		}
	}
	public Vector<int[]> get_CNum(){
		return NumC;
	}
	public int get_EdgeNum()
	{
		return Num_Edge;
	}
	public int[] get_Paint()
	{
		return Colors;
	}
	public int get_divNum()
	{
		return Color;
	}
}
