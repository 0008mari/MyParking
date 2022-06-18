import React, { useEffect } from "react";

function Map() {
  useEffect(() => {
    let container = document.getElementById("map");

    let options = {
      center: new window.kakao.maps.LatLng(
        37.365264512305174,
        127.10676860117488
      ),
      level: 5,
    };

    let map = new window.kakao.maps.Map(container, options);
  }, []);

  return (
    <div>
      <div id="map" style={{ width: "100%", height: "100vh" }}></div>
    </div>
  );
}

export default Map;
