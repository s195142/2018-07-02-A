package it.polito.tdp.extflightdelays.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Map<Integer, Airport> aidmap;
	private SimpleWeightedGraph<Airport, DefaultWeightedEdge> grafo;
	
	public Model() {
		aidmap = new HashMap<>();
	}

	public boolean isDigit(String distanza) {
		// TODO Auto-generated method stub
		return distanza.matches("\\d+");
	}

	public void creaGrafo(String distanza) {
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		dao.loadAllAirports(aidmap);
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, aidmap.values());
		
		List<Rotta> rotte = dao.getRotte(aidmap, distanza);
		for(Rotta r : rotte) {
			DefaultWeightedEdge edge = grafo.getEdge(r.getOrig(), r.getDest());
			if(edge==null) {
				Graphs.addEdgeWithVertices(grafo, r.getOrig(), r.getDest(), r.getPeso());
			}
		}
	
		System.out.println("Grafo creato! Vertici: "+grafo.vertexSet().size()+" Archi: "+grafo.edgeSet().size()+"\n");
		
	}
	
	public List<Airport> getVertici(){
		List<Airport> vertici = new LinkedList<>();
		for(Airport ap : grafo.vertexSet()) {
			vertici.add(ap);
		}
		return vertici;
	}

	public String getConnessi(Airport a) {
		String result = "";
		List<Airport> vicini = Graphs.neighborListOf(grafo, a);
		Collections.sort(vicini, new Comparator<Airport>() {

			@Override
			public int compare(Airport o1, Airport o2) {
				DefaultWeightedEdge edge1 = grafo.getEdge(a, o1);
				double peso1 = grafo.getEdgeWeight(edge1);
				DefaultWeightedEdge edge2 = grafo.getEdge(a, o2);
				double peso2 = grafo.getEdgeWeight(edge2);
				return (int) (peso2-peso1);
			}
		});
		
		for(Airport ap : vicini) {
			DefaultWeightedEdge edge = grafo.getEdge(a, ap);
			result += ap.getAirportName()+" Distanza media: "+grafo.getEdgeWeight(edge)+"\n";
		}
		
		return result;
	}

}
