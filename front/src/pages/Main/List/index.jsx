import { useNavigate } from "react-router-dom";
import { List, Skeleton } from "antd";

function MainList({ parkings }) {
  const navigate = useNavigate();

  const handleItemClick = (code) => {
    navigate(`/review/${code}`);
  };

  if (!parkings) return <Skeleton />;

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
        dataSource={parkings}
        renderItem={(item, i) => (
          <List.Item key={item.code} onClick={() => handleItemClick(item.code)}>
            <List.Item.Meta
              avatar={String.fromCharCode("A".charCodeAt(0) + i)}
              title={item.name}
              description={`리뷰 ${
                item.reviewCount
              }개, 평점 ${item.reviewStarAvg.toFixed(2)}`}
            />
            {item.address}
          </List.Item>
        )}
      />
    </div>
  );
}

export default MainList;
