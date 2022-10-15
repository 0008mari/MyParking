import { useState } from "react";
import { Button } from "antd";

import ReviewList from "./List";

import { PageWrapper } from "./styled";

function ReviewPage() {
  const [selectedReview, setSelectedReview] = useState({});

  const handleButtonClick = (e, index) => {
    setSelectedReview((prev) => ({ ...prev, [index]: e.target.value }));
  };

  const handleRateClick = (value) => {
    setSelectedReview((prev) => ({ ...prev, 5: value }));
  };

  const handleSubmitReview = (e) => {
    e.preventDefault();
    if (
      !selectedReview[0] ||
      !selectedReview[1] ||
      !selectedReview[2] ||
      !selectedReview[3] ||
      !selectedReview[4] ||
      !selectedReview[5]
    ) {
      console.log("error");
      return;
    }

    console.log(selectedReview);
  };

  return (
    <PageWrapper>
      <h1>리뷰쓰기</h1>
      <ReviewList onClick={handleButtonClick} onChange={handleRateClick} />
      <Button size="large" onClick={handleSubmitReview}>
        리뷰 남기기
      </Button>
    </PageWrapper>
  );
}

export default ReviewPage;
