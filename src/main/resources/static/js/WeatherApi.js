(()=>{
    weatherFn(document.getElementById("search").value);
})();

function weatherSearch(){
    // 해당하는 날씨를 id="map"에 표시
    weatherFn(document.getElementById("search").value);
}

function weatherFn(location){
    let lat, lon;

    switch(location) {
        case "Songpa":
            lat = 37.514856398566735;
            lon = 127.10323168435833;
            break;
        case "Nowon":
            lat = 37.65496749140469;
            lon = 127.061297075607;
            break;
        case "Paju":
            lat = 37.83472924837163;
            lon = 126.73551132667517;
            break;
        case "Gangbuk":
            lat = 37.62227771123334;
            lon = 127.0397961958558;
            break;
        case "Mapo":
            lat = 37.557641983466915;
            lon = 126.92587776948747;
            break;
        default:
            lat = 37.5665;
            lon = 126.9780;
    }

    alert(`${location}`);

    const apiUrl = `/api/weatherList?lat=${lat}&lon=${lon}`;

    $.ajax({
        url: apiUrl,
        dataType: 'json',
        type: 'GET',
        success: function(result){
            console.log(result); // weatherList:{}


            const weatherList = JSON.parse(result.weatherList);

            const temp_max = (weatherList.main.temp_max - 273.15).toFixed(1);
            const temp_min = (weatherList.main.temp_min - 273.15).toFixed(1);
            const name = location;

            const humidity = weatherList.main.humidity;

            const icon = weatherList.weather[0].icon; // 아이콘 정보 가져오기
            const iconUrl = `http://openweathermap.org/img/wn/${icon}@2x.png`; // 아이콘 URL 생성

            const description = weatherList.weather[0].description;
            const temp = (weatherList.main.temp - 273.15).toFixed(1);

            const speed = weatherList.wind.speed;
            const clouds_all = weatherList.clouds.all;

            /////////////////////////////////////////////////

            $('#city').text(name);
            $('#temp_max').text(temp_max);
            $('#temp_min').text(temp_min);

            $('#humidity').text(humidity);

            $('#weather_icon').attr('src', iconUrl); // 아이콘 설정

            $('#description').text(description);
            $('#temp').text(temp);

            $('#speed').text(speed);
            $('#clouds_all').text(clouds_all);

            kakaoFn(lat, lon);
        }
    });
}

function kakaoFn(lat, lon){
    alert(`${lat}, ${lon}`);

    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 지도를 클릭한 위치에 표출할 마커입니다
    var marker = new kakao.maps.Marker({
        // 지도 중심좌표에 마커를 생성합니다
        position: map.getCenter()
    });
    // 지도에 마커를 표시합니다
    marker.setMap(map);

    // 지도에 클릭 이벤트를 등록합니다
    // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
    kakao.maps.event.addListener(map, 'click', function (mouseEvent) {
        // 클릭한 위도, 경도 정보를 가져옵니다
        var latlng = mouseEvent.latLng;

        // 마커 위치를 클릭한 위치로 옮깁니다
        marker.setPosition(latlng);

        var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
        message += '경도는 ' + latlng.getLng() + ' 입니다';

        var resultDiv = document.getElementById('clickLatlng');
        resultDiv.innerHTML = message;
    });
}