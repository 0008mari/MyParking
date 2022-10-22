/* global kakao */
import React, { useEffect } from "react";

const { kakao } = window;
function Map({ setItems }) {
  useEffect(() => {
    let container = document.getElementById("map");
    var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    let dong = "";

    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        const lat = position.coords.latitude; // 위도
        const lon = position.coords.longitude; // 경도

        let options = {
          center: new kakao.maps.LatLng(lat, lon),
          level: 5,
        };
        let map = new kakao.maps.Map(container, options);

        var coord = new kakao.maps.LatLng(lat, lon);
        let geocoder = new kakao.maps.services.Geocoder();
        geocoder.coord2RegionCode(
          coord.getLng(),
          coord.getLat(),
          (result, status) => {
            if (status === kakao.maps.services.Status.OK) {
              const split = result[1].address_name.split(" ");
              dong = split[split.length - 1];

              // 키워드로 장소를 검색합니다
              let ps = new kakao.maps.services.Places();
              ps.keywordSearch(`${dong} 주차장`, placesSearchCB);
            }
          }
        );

        function placesSearchCB(data, status, pagination) {
          setItems(data);
          if (status === window.kakao.maps.services.Status.OK) {
            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
            // LatLngBounds 객체에 좌표를 추가합니다
            var bounds = new window.kakao.maps.LatLngBounds();

            for (var i = 0; i < data.length; i++) {
              displayMarker(data[i]);
              bounds.extend(new window.kakao.maps.LatLng(data[i].y, data[i].x));
            }

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
            map.setBounds(bounds);
          }
        }

        function displayMarker(place) {
          // 마커를 생성하고 지도에 표시합니다
          var marker = new window.kakao.maps.Marker({
            map: map,
            position: new window.kakao.maps.LatLng(place.y, place.x),
          });

          // 마커에 클릭이벤트를 등록합니다
          window.kakao.maps.event.addListener(marker, "click", function () {
            // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
            infowindow.setContent(
              '<div style="padding:5px;font-size:12px;">' +
                place.place_name +
                "</div>"
            );
            infowindow.open(map, marker);
          });
        }
      });
    } else {
      let options = {
        center: new window.kakao.maps.LatLng(
          37.365264512305174,
          127.10676860117488
        ),
        level: 5,
      };

      let map = new window.kakao.maps.Map(container, options);
    }
  }, []);

  return (
    <div>
      <div id="map" style={{ width: "100%", height: "100vh" }}></div>
    </div>
  );
}

export default Map;
