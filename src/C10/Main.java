package C10;


public class Main {

	public static void main(String[] args) {
		Graph graph=new Graph(8);
		/*
		graph.add_Edge(1,2);
		graph.add_Edge(1,3);
		graph.add_Edge(2,3);
		graph.add_Edge(3,5);
		graph.add_Edge(4,5);
		graph.add_Edge(4,6);
		graph.add_Edge(5,6);
		graph.add_Edge(5,8);
		graph.add_Edge(6,7);
		*/
		graph.add_Edge(1,2,45);
		graph.add_Edge(1,3,4);
		graph.add_Edge(2,3,30);
		graph.add_Edge(3,5,1);
		graph.add_Edge(4,5,20);
		graph.add_Edge(4,6,25);
		graph.add_Edge(5,6,23);
		graph.add_Edge(5,8,5);
		graph.add_Edge(6,7,7);
		
		GN gn=new GN(graph);
		gn.run();
		gn.output();

	}

}
