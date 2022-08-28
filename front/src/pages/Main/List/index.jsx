import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { List, Skeleton, Divider } from "antd";
import InfiniteScroll from "react-infinite-scroll-component";

function InfiniteList({ items }) {
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
        endMessage={<Divider plain>It is all, nothing more 🤐</Divider>}
        scrollableTarget="scrollableDiv"
      >
        <List
          dataSource={items}
          renderItem={(item, i) => (
            <List.Item key={item.id} onClick={handleItemClick}>
              <List.Item.Meta
                avatar={String.fromCharCode("A".charCodeAt(0) + i)}
                title={item.place_name}
                description={`평점 4.5`}
              />
              {item.address_name}
            </List.Item>
          )}
        />
      </InfiniteScroll>
    </div>
  );
}

export default InfiniteList;
