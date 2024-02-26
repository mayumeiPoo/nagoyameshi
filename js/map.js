
$(function() {
   // ボタンがクリックされた場合
   $('button').on('click', function(){
     function getCoordinates() {
            const address = $('#addressInput').val();

            // Google Geocoding API key (replace with your own key)
            const apiKey = 'AIzaSyCtPm_1RFZHhvrBw95zXKoCHwbba26HJqw';

            const geocodingUrl = `https://maps.googleapis.com/maps/api/geocode/json?address=${encodeURIComponent(address)}&key=${apiKey}`;

            $.ajax({
                url: geocodingUrl,
                method: 'GET',
                dataType: 'json',
                success: function(data) {
                    if (data.status === 'OK') {
                        const location = data.results[0].geometry.location;
                        const latitude = location.lat;
                        const longitude = location.lng;

                        console.log(`Address: ${address}`);
                        console.log(`Latitude: ${latitude}, Longitude: ${longitude}`);
                    } else {
                        console.error(`Geocoding failed with status: ${data.status}`);
                    }
                },
                error: function(error) {
                    console.error('Error fetching data:', error);
                }
            });
        }
        })
        })