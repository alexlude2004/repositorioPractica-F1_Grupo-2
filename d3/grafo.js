var nodes = new vis.DataSet([
	{ id: 1, label: 'Estacion 1', color: 'yellow' },
	{ id: 2, label: 'Estacion 2', color: 'yellow' },
	{ id: 3, label: 'Estacion 3', color: 'yellow' },
	{ id: 4, label: 'Estacion 4', color: 'yellow' },
	{ id: 5, label: 'Estacion 5', color: 'yellow' },
	{ id: 6, label: 'Estacion 6', color: 'yellow' },
	{ id: 7, label: 'Estacion 7', color: 'yellow' },
	{ id: 8, label: 'Estacion 8', color: 'yellow' },
	{ id: 9, label: 'Estacion 9', color: 'yellow' },
	{ id: 10, label: 'Estacion 10', color: 'yellow' },
]);
var edges = new vis.DataSet([
	{ from: 1, to: 2, label: '9.23', color: 'lightblue' },
	{ from: 1, to: 7, label: '7.92', color: 'lightblue' },
	{ from: 1, to: 4, label: '6.02', color: 'lightblue' },
	{ from: 1, to: 5, label: '5.09', color: 'lightblue' },
	{ from: 2, to: 1, label: '9.23', color: 'lightblue' },
	{ from: 2, to: 4, label: '7.6', color: 'lightblue' },
	{ from: 2, to: 9, label: '3.42', color: 'lightblue' },
	{ from: 2, to: 3, label: '5.99', color: 'lightblue' },
	{ from: 2, to: 6, label: '7.57', color: 'lightblue' },
	{ from: 2, to: 8, label: '3.95', color: 'lightblue' },
	{ from: 3, to: 2, label: '5.99', color: 'lightblue' },
	{ from: 3, to: 8, label: '4.43', color: 'lightblue' },
	{ from: 3, to: 7, label: '1.58', color: 'lightblue' },
	{ from: 4, to: 2, label: '7.6', color: 'lightblue' },
	{ from: 4, to: 1, label: '6.02', color: 'lightblue' },
	{ from: 5, to: 1, label: '5.09', color: 'lightblue' },
	{ from: 5, to: 8, label: '9.54', color: 'lightblue' },
	{ from: 6, to: 10, label: '6.08', color: 'lightblue' },
	{ from: 6, to: 2, label: '7.57', color: 'lightblue' },
	{ from: 6, to: 8, label: '5.39', color: 'lightblue' },
	{ from: 6, to: 9, label: '5.6', color: 'lightblue' },
	{ from: 7, to: 1, label: '7.92', color: 'lightblue' },
	{ from: 7, to: 3, label: '1.58', color: 'lightblue' },
	{ from: 7, to: 10, label: '3.4', color: 'lightblue' },
	{ from: 8, to: 3, label: '4.43', color: 'lightblue' },
	{ from: 8, to: 5, label: '9.54', color: 'lightblue' },
	{ from: 8, to: 2, label: '3.95', color: 'lightblue' },
	{ from: 8, to: 6, label: '5.39', color: 'lightblue' },
	{ from: 8, to: 9, label: '0.53', color: 'lightblue' },
	{ from: 9, to: 2, label: '3.42', color: 'lightblue' },
	{ from: 9, to: 6, label: '5.6', color: 'lightblue' },
	{ from: 9, to: 8, label: '0.53', color: 'lightblue' },
	{ from: 10, to: 6, label: '6.08', color: 'lightblue' },
	{ from: 10, to: 7, label: '3.4', color: 'lightblue' },
]);
var container = document.getElementById("mynetwork");
var data = {
  nodes: nodes,
  edges: edges,
};
var options = {};
var network = new vis.Network(container, data, options);