import React from "react";
import { useQuery } from "@tanstack/react-query";
import { Input } from "antd";

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
const { Search } = Input;

function Main() {
  const [searchKeyword, setSearchKeyword] = useState([]);

  const handleSearch = (value) => {
    setSearchKeyword(value);
  };

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
        <List searchKeyword={searchKeyword} />
      </LeftWrapper>
      <RightWrapper>
        <Map setItems={setSearchKeyword} />
      </RightWrapper>
    </RowFlexWrapper>
  );
}

export default Main;
