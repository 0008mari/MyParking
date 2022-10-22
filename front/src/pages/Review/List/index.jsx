import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { List, Skeleton, Divider, Rate, Avatar } from "antd";
import { UserOutlined } from "@ant-design/icons";
import InfiniteScroll from "react-infinite-scroll-component";

import {
  RowFlexWrapper,
  RowFlexWrapperSpace,
  ColumnFlexWrapper,
  ReviewItem,
} from "./style";

function ReviewList({ items }) {
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState([]);

  const navigate = useNavigate();

  const loadMoreData = () => {
    if (loading) {
      return;
    }
    setLoading(true);
    fetch(
      "https://randomuser.me/api/?results=10&inc=name,gender,email,nat,picture&noinfo"
    )
      .then((res) => res.json())
      .then((body) => {
        setData([...data, ...body.results]);
        setLoading(false);
      })
      .catch(() => {
        setLoading(false);
      });
  };

  const handleItemClick = () => {
    navigate("/review/1");
  };

  useEffect(() => {
    loadMoreData();
  }, []);

  return (
    <div
      id="scrollableDiv"
      style={{
        height: "85vh",
        width: "100%",
        overflow: "auto",
        padding: "0 16px",
        border: "1px solid rgba(140, 140, 140, 0.35)",
      }}
    >
      <InfiniteScroll
        dataLength={data.length}
        next={loadMoreData}
        hasMore={data.length < 50}
        loader={<Skeleton avatar paragraph={{ rows: 1 }} active />}
        endMessage={<Divider plain>모든 데이터를 받아왔습니다</Divider>}
        scrollableTarget="scrollableDiv"
      >
        <List
          dataSource={items}
          renderItem={(item, i) => (
            <List.Item key={item.id} onClick={handleItemClick}>
              <List.Item />
              <Review />
            </List.Item>
          )}
        />
      </InfiniteScroll>
    </div>
  );
}

function Review({ item }) {
  return (
    <ColumnFlexWrapper>
      <RowFlexWrapper>
        <ReviewItem>넓어요</ReviewItem>
        <ReviewItem>넓어요</ReviewItem>
        <ReviewItem>넓어요</ReviewItem>
        <ReviewItem>넓어요</ReviewItem>
        <ReviewItem>넓어요</ReviewItem>
      </RowFlexWrapper>
      <RowFlexWrapperSpace>
        <Rate disabled defaultValue={2} />
        <RowFlexWrapper>
          <Avatar size="small" icon={<UserOutlined />} />
          <div>홍컴님</div>
        </RowFlexWrapper>
      </RowFlexWrapperSpace>
    </ColumnFlexWrapper>
  );
}
export default ReviewList;
