var osmUrl = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
                    osmAttrib = '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
                    osm = L.tileLayer(osmUrl, {maxZoom: 15, attribution: osmAttrib});

            var map = L.map('map').setView([-4.036, -79.201], 15);

            L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(map);
L.marker([-4.29949,-78.99392]).addTo(map)
.bindPopup(' Estacion 1 ')
.openPopup();
L.marker([-4.29744,-78.91084]).addTo(map)
.bindPopup(' Estacion 2 ')
.openPopup();
L.marker([-4.33714,-78.94727]).addTo(map)
.bindPopup(' Estacion 3 ')
.openPopup();
L.marker([-4.34301,-78.96179]).addTo(map)
.bindPopup(' Estacion 4 ')
.openPopup();
L.marker([-4.34523,-78.99268]).addTo(map)
.bindPopup(' Estacion 5 ')
.openPopup();
L.marker([-4.35123,-78.95252]).addTo(map)
.bindPopup(' Estacion 6 ')
.openPopup();
L.marker([-4.33658,-78.93301]).addTo(map)
.bindPopup(' Estacion 7 ')
.openPopup();
L.marker([-4.33281,-78.90761]).addTo(map)
.bindPopup(' Estacion 8 ')
.openPopup();
L.marker([-4.32802,-78.90778]).addTo(map)
.bindPopup(' Estacion 9 ')
.openPopup();
L.marker([-4.32305,-78.90555]).addTo(map)
.bindPopup(' Estacion 10 ')
.openPopup();
