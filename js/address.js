
        function getCoordinates() {
            const address = document.getElementById('addressInput').value;
            
            
            const apiKey = 'AIzaSyCtPm_1RFZHhvrBw95zXKoCHwbba26HJqw';

            const geocodingUrl = `https://maps.googleapis.com/maps/api/geocode/json?address=${encodeURIComponent(address)}&key=${apiKey}`;

            fetch(geocodingUrl)
                .then(response => response.json())
                .then(data => {
                    if (data.status === 'OK') {
                        const location = data.results[0].geometry.location;
                        const latitude = location.lat;
                        const longitude = location.lng;

                        console.log(`Address: ${address}`);
                        console.log(`Latitude: ${latitude}, Longitude: ${longitude}`);
                        
                        
                    } else {
                        console.error(`Geocoding failed with status: ${data.status}`);
                    }
                })
                .catch(error => console.error('Error fetching data:', error));
        }
        
