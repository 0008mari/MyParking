import { useMutation, useQuery } from "@tanstack/react-query";
import { Button, Empty, List, Rate } from "antd";
import styled from "styled-components";
import { appClient } from "../../api";

function MyPage() {
  const userId = localStorage.getItem("userId");

  const { data: reviews, isLoading } = useQuery(["my"], async () => {
    const { data } = await appClient.get(`/reviews`, {
      params: { userId: userId },
    });
    return data;
  });

  const { mutate } = useMutation(
    (id) => {
      appClient.delete(`/reviews/${id}`);
    },
    {
      onSuccess: () => {
        alert("삭제 완료");
      },
    }
  );

  const handleDelete = (id) => {
    mutate(id);
  };

  if (!reviews) return <Empty image={Empty.PRESENTED_IMAGE_SIMPLE} />;

  return (
    <PageWrapper>
      <h1>마이페이지</h1>
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
              <FlexWrapper>
                <ItemContainter>
                  <ItemBox>{item.space}</ItemBox>
                  <ItemBox>{item.level}</ItemBox>
                  <ItemBox>{item.costefficient}</ItemBox>
                  <ItemBox>{item.staff}</ItemBox>
                  <ItemBox>{item.revisit}</ItemBox>
                </ItemContainter>
                <Rate disabled defaultValue={item.score} />
                <Button onClick={() => handleDelete(item.reviewId)}>
                  삭제하기
                </Button>
              </FlexWrapper>
            </List.Item>
          )}
        />
      </div>
    </PageWrapper>
  );
}

const FlexWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 16px 16px 0 16px;
  gap: 8px;

  button {
    margin-top: 10px;
  }
`;

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

const PageWrapper = styled.div`
  display: flex;
  flex-direction: column;

  align-items: center;
  gap: 20px;
  margin: 20px;

  button {
    margin-bottom: 20px;
  }
`;

export default MyPage;
