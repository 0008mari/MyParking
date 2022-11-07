import React from "react";

import ReviewList from "./List";
import AverageReview from "./AverageReview";
import { useParams } from "react-router-dom";

function ReviewPage() {
  const { parkingId } = useParams();

  return (
    <>
      <AverageReview parkingId={parkingId} />
      <ReviewList parkingId={parkingId} />
    </>
  );
}

export default ReviewPage;
