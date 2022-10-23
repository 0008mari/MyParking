import { useNavigate } from "react-router-dom";
import { List, Skeleton } from "antd";
import { useQuery } from "@tanstack/react-query";
import { appClient } from "../../../api";

function MainList({ searchKeyword }) {
  const navigate = useNavigate();

  const handleItemClick = () => {
    navigate("/review/1");
  };

  const { data: parkings, isLoading } = useQuery(["parking"], async () => {
    const { data } = await appClient.get(`/parkings`, {
      params: { address: searchKeyword },
    });
    return data;
  });

  if (isLoading || !parkings) return <Skeleton />;

  console.log(parkings.data.parkingList);

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
        dataSource={parkings.data.parkingList}
        renderItem={(item, i) => (
          <List.Item key={item.id} onClick={handleItemClick}>
            <List.Item.Meta
              avatar={String.fromCharCode("A".charCodeAt(0) + i)}
              title={item.name}
              description={`평점 4.5`}
            />
            {item.address}
          </List.Item>
        )}
      />
    </div>
  );
}

export default MainList;
