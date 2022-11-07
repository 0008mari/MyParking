import { useQuery } from "@tanstack/react-query";
import { Empty, List, Rate } from "antd";
import styled from "styled-components";
import { appClient } from "../../../api";

function ReviewList({ parkingId }) {
  const { data: reviews, isLoading } = useQuery(
    ["reviews", parkingId],
    async () => {
      const { data } = await appClient.get(`/reviews`, {
        params: { code: parkingId },
      });
      return data;
    }
  );

  if (!reviews) return <Empty image={Empty.PRESENTED_IMAGE_SIMPLE} />;

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
      <List
        dataSource={reviews.data.reviewList}
        renderItem={(item, i) => (
          <List.Item key={reviews.data.reviewId}>
            <ItemContainter>
              <ItemBox>{item.space}</ItemBox>
              <ItemBox>{item.level}</ItemBox>
              <ItemBox>{item.costefficient}</ItemBox>
              <ItemBox>{item.staff}</ItemBox>
              <ItemBox>{item.revisit}</ItemBox>
            </ItemContainter>
            <Rate disabled defaultValue={item.score} />
          </List.Item>
        )}
      />
    </div>
  );
}

const ItemContainter = styled.div`
  display: flex;
  gap: 10px;
`;
const ItemBox = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 70px;
  height: 30px;
  border: solid 1px black;
  border-radius: 4px;
`;

export default ReviewList;
