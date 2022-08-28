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
import { useState } from "react";
const { Search } = Input;

const onSearch = (value) => console.log(value);

function Main() {
  const [items, setItems] = useState([]);
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
        <InfiniteList items={items} />
      </LeftWrapper>
      <RightWrapper>
        <Map setItems={setItems} />
      </RightWrapper>
    </RowFlexWrapper>
  );
}

export default Main;
