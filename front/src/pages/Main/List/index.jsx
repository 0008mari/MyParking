import React, { useState, useEffect } from "react";
import { List, Skeleton, Divider } from "antd";
import InfiniteScroll from "react-infinite-scroll-component";

function InfiniteList() {
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState([]);

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

  useEffect(() => {
    loadMoreData();
  }, []);

  return (
    <div
      id="scrollableDiv"
      style={{
        height: "100vh",
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
        endMessage={<Divider plain>It is all, nothing more π€</Divider>}
        scrollableTarget="scrollableDiv"
      >
        <List
          dataSource={data}
          renderItem={(item) => (
            <List.Item key={item.id}>
              <List.Item.Meta
                avatar={"A"}
                title={`νμ΅λ μ£Όμ°¨μ₯`}
                description={`νμ  4.5`}
              />
              {`μμΈνΉλ³μ λ§ν¬κ΅¬ μμ°μ°λ‘ 94`}
            </List.Item>
          )}
        />
      </InfiniteScroll>
    </div>
  );
}

export default InfiniteList;
