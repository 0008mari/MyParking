import React from "react";
import { Input, Spin } from "antd";
import { LoadingOutlined } from "@ant-design/icons";

import InfoDrawer from "./Drawer";
import List from "./List";
import Map from "../Map";

import {
  RowFlexWrapper,
  LeftWrapper,
  RightWrapper,
  FlexWrapper,
} from "./style";
import { useState } from "react";
import { useEffect } from "react";
import useCurrentLocation from "./hooks/useCurrentLocation";
import { useQuery } from "@tanstack/react-query";
import { appClient } from "../../api";
import styled from "styled-components";
const antIcon = (
  <LoadingOutlined
    style={{
      fontSize: 50,
    }}
    spin
  />
);
const { Search } = Input;

const geolocationOptions = {
  enableHighAccuracy: true,
  timeout: 1000 * 60 * 1, // 1 min (1000 ms * 60 sec * 1 minute = 60 000ms)
  maximumAge: 1000 * 3600 * 24, // 24 hour
};

const { kakao } = window;

function Main() {
  const [searchKeyword, setSearchKeyword] = useState("");
  const { location: currentLocation, error: currentError } =
    useCurrentLocation(geolocationOptions);

  const { data: parkings, isLoading } = useQuery(
    ["parking", searchKeyword],
    async () => {
      const { data } = await appClient.get(`/parkings`, {
        params: { address: searchKeyword },
      });
      return data;
    },
    {
      enabled: !!searchKeyword,
    }
  );

  const handleSearch = (value) => {
    setSearchKeyword(value);
  };

  function getAddr(lat, lng) {
    // 주소-좌표 변환 객체를 생성합니다
    let geocoder = new kakao.maps.services.Geocoder();

    let coord = new kakao.maps.LatLng(lat, lng);
    let callback = function (result, status) {
      if (status === kakao.maps.services.Status.OK) {
        const arr = { ...result };
        const currentDong = arr[0].address.region_3depth_name;
        setSearchKeyword(currentDong);
      }
    };
    geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
  }

  useEffect(() => {
    if (currentLocation) {
      getAddr(currentLocation.latitude, currentLocation.longitude);
    }
  }, [currentLocation]);

  if (!currentLocation | isLoading) {
    return (
      <Center>
        <Spin indicator={antIcon} />
      </Center>
    );
  }

  return (
    <RowFlexWrapper>
      <LeftWrapper>
        <FlexWrapper>
          <InfoDrawer />
          <Search
            placeholder="장소를 입력해주세요"
            allowClear
            onSearch={handleSearch}
            style={{ width: "100%" }}
          />
        </FlexWrapper>
        <List parkings={parkings.data.parkingList} />
      </LeftWrapper>
      <RightWrapper>
        <Map
          lat={currentLocation.latitude}
          lon={currentLocation.longitude}
          parkings={parkings.data.parkingList}
        />
      </RightWrapper>
    </RowFlexWrapper>
  );
}

const Center = styled.div`
  display: flex;
  height: 100vh;
  align-items: center;
  justify-content: center;
`;

export default Main;
