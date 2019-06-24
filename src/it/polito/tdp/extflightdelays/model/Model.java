package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> grafo;
	private Map<Integer, Airport> idMapAirport;
	private ExtFlightDelaysDAO dao;
	private List<VoliDistanza> voliConAvgDistanza;
	
	public Model() {
		this.dao = new ExtFlightDelaysDAO();
		this.idMapAirport = new HashMap<>();
		
		for(Airport a : dao.loadAllAirports()) {//popola mappa con tutti gli aereoporti
			idMapAirport.put(a.getId(), a);
		}
	}
	
	public void creaGrafo(int limite) {
		
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		voliConAvgDistanza = dao.getVoliAndAVG(limite, idMapAirport);
		
		for(VoliDistanza vd : voliConAvgDistanza) {//inserimento vertici
			if(!this.grafo.containsVertex(vd.getIdPartenza())) {
				grafo.addVertex(vd.getIdPartenza());
			}
		}
		
		for(VoliDistanza vd : voliConAvgDistanza) {
			Graphs.addEdgeWithVertices(grafo, vd.getIdPartenza(), vd.getIdArrivo(), vd.getDistanzaAVG());
		}
		
		//System.out.println("Grafo creato!!\n#vertici: "+grafo.vertexSet().size()+"\n#archi: "+grafo.edgeSet().size());
		
		
	}

	public Graph<Airport, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
	public List<VoliDistanza> getConnessi(Airport partenza){
		
		List<VoliDistanza> result= new ArrayList<>();
		
		for(Airport a : Graphs.neighborListOf(grafo, partenza)) {
			
			VoliDistanza vd = new VoliDistanza(partenza, a, grafo.getEdgeWeight(grafo.getEdge(partenza, a)));
			result.add(vd);
			
		}
		Collections.sort(result);
		
		return result;
		
	}
	
	

}
