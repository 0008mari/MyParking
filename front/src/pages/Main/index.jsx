import { Input } from "antd";

import InfoDrawer from "./Drawer";
import InfiniteList from "./List";

import { LeftWrapper, FlexWrapper } from "./style";
const { Search } = Input;

const onSearch = (value) => console.log(value);

function Main() {
  return (
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
  );
}

export default Main;
