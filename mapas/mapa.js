var osmUrl = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
                    osmAttrib = '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
                    osm = L.tileLayer(osmUrl, {maxZoom: 15, attribution: osmAttrib});

            var map = L.map('map').setView([-4.036, -79.201], 15);

            L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(map);
L.marker([-96262.0,-5654.0]).addTo(map)
.bindPopup(' Estacion 1 ')
.openPopup();
L.marker([-9655.0,-95956.0]).addTo(map)
.bindPopup(' Estacion 2 ')
.openPopup();
L.marker([-79456.0,-84545.0]).addTo(map)
.bindPopup(' Estacion 3 ')
.openPopup();
