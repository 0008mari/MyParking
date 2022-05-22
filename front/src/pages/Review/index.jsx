import { Button, Col } from "antd";
import ReviewList from "./List";

function ReviewPage() {
  return (
    <Col justify="center" style={{ width: "500px" }}>
      <h1>리뷰쓰기</h1>
      <ReviewList />
      <Button>리뷰 남기기</Button>
    </Col>
  );
}

export default ReviewPage;
