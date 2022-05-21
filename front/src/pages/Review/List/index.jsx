import { List, Button, Row, Col, Rate } from "antd";

const data = [
  { title: "주차장 넓이", select: ["넓어요", "적당해요", "좁아요"] },
  { title: "주차 난이도", select: ["쉬워요", "적당해요", "어려워요"] },
  { title: "주차장 가격", select: ["저렴해요", "적당해요", "비싸요"] },
  { title: "친절도", select: ["넓어요", "적당해요", "좁아요"] },
  { title: "재방문 의사", select: ["또 올께요", "재방문x"] },
  { title: "별점", select: [] },
];

function ReviewList() {
  return (
    <>
      <List
        bordered
        dataSource={data}
        renderItem={(item) => (
          <List.Item style={{ padding: "30px" }}>
            <Col>
              <div>{item.title}</div>
              <Row>
                {item.select.map((selectBtn) => (
                  <Button>{selectBtn}</Button>
                ))}
                {item.select.length === 0 && <Rate />}
              </Row>
            </Col>
          </List.Item>
        )}
      />
    </>
  );
}

export default ReviewList;
