var nodes = new vis.DataSet([
	{ id: 1, label: 'Estacion 1', color: 'yellow' },
	{ id: 2, label: 'Estacion 2', color: 'yellow' },
	{ id: 3, label: 'Estacion 3', color: 'yellow' },
]);
var edges = new vis.DataSet([
]);
var container = document.getElementById("mynetwork");
var data = {
  nodes: nodes,
  edges: edges,
};
var options = {
  layout: {
    improvedLayout: true
  },
  physics: {
    barnesHut: {
      gravitationalConstant: -2000,
      centralGravity: 0.3,
      springLength: 230,
      springConstant: 0.05,
      damping: 0.09,
      avoidOverlap: 0.1
    }
  },
  edges: {
    smooth: {
      type: 'continuous'
    },
    color: 'darkblue',
    width: 2
  }
};
var network = new vis.Network(container, data, options);