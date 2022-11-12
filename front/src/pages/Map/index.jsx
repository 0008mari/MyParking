/* global kakao */
import React, { useEffect } from "react";

const { kakao } = window;
function Map({ lat, lon, parkings }) {
  useEffect(() => {
    let container = document.getElementById("map");
    var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

    let options = {
      center: new kakao.maps.LatLng(lat, lon),
      level: 5,
    };

    var bounds = new window.kakao.maps.LatLngBounds();

    let map = new kakao.maps.Map(container, options);
    if (parkings) {
      for (var i = 0; i < parkings.length; i++) {
        displayMarker(parkings[i]);
        bounds.extend(
          new kakao.maps.LatLng(parkings[i].latitude, parkings[i].longitude)
        );
      }
    }

    map.setBounds(bounds);

    function displayMarker(place) {
      // 마커를 생성하고 지도에 표시합니다
      var marker = new window.kakao.maps.Marker({
        map: map,
        position: new window.kakao.maps.LatLng(place.latitude, place.longitude),
      });

      // 마커에 클릭이벤트를 등록합니다
      window.kakao.maps.event.addListener(marker, "click", function () {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent(
          '<div style="padding:5px;font-size:12px;">' + place.name + "</div>"
        );
        infowindow.open(map, marker);
      });
    }
  }, [parkings]);

  return (
    <div>
      <div id="map" style={{ width: "100%", height: "100vh" }}></div>
    </div>
  );
}

export default Map;
