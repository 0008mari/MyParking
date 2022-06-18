import { Button } from "antd";
import ReviewList from "./List";

import { PageWrapper } from "./styled";

function ReviewPage() {
  return (
    <PageWrapper>
      <h1>리뷰쓰기</h1>
      <ReviewList />
      <Button size="large">리뷰 남기기</Button>
    </PageWrapper>
  );
}

export default ReviewPage;
