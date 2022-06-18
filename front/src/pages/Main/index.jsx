import React from "react";
import { Input } from "antd";

import InfoDrawer from "./Drawer";
import InfiniteList from "./List";
import Map from "../Map";

import {
  RowFlexWrapper,
  LeftWrapper,
  RightWrapper,
  FlexWrapper,
} from "./style";
const { Search } = Input;

const onSearch = (value) => console.log(value);

function Main() {
  return (
    <RowFlexWrapper>
      <LeftWrapper>
        <FlexWrapper>
          <InfoDrawer />
          <Search
            placeholder="장소를 입력해주세요"
            allowClear
            onSearch={onSearch}
            style={{ width: "100%" }}
          />
        </FlexWrapper>
        <InfiniteList />
      </LeftWrapper>
      <RightWrapper>
        <Map />
      </RightWrapper>
    </RowFlexWrapper>
  );
}

export default Main;
